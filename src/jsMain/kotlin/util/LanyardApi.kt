package util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*


@Serializable
data class Presence(
    @SerialName("active_on_discord_desktop")
    val activeOnDiscordDesktop: Boolean,
    @SerialName("active_on_discord_mobile")
    val activeOnDiscordMobile: Boolean,
    @SerialName("active_on_discord_web")
    val activeOnDiscordWeb: Boolean,
    val activities: List<Activity>,
    @SerialName("discord_status")
    val discordStatus: String,
    @SerialName("discord_user")
    val discordUser: DiscordUser,
    @SerialName("listening_to_spotify")
    val listeningToSpotify: Boolean,
    val spotify: JsonObject?
) {
    @Serializable
    data class Activity(
        @SerialName("application_id")
        val applicationId: String,
        val assets: Assets,
        @SerialName("created_at")
        val createdAt: Long,
        val details: String? = null,
        val id: String,
        val name: String,
        val party: Party? = null,
        val state: String? = null,
        val timestamps: Timestamps,
        val type: Int
    ) {
        @Serializable
        data class Assets(
            @SerialName("large_image")
            val largeImage: String,
            @SerialName("large_text")
            val largeText: String,
            @SerialName("small_image")
            val smallImage: String? = null,
            @SerialName("small_text")
            val smallText: String? = null
        )

        @Serializable
        data class Party(val id: String)
    }

    @Serializable
    data class DiscordUser(
        val avatar: String,
        val bot: Boolean,
        val discriminator: String,
        val id: String,
        @SerialName("public_flags")
        val publicFlags: Int,
        val username: String
    )

    @Serializable
    data class Timestamps(
        val start: Long,
        val end: Long? = null
    )
}

@Serializable(with = OpCode.Serializer::class)
enum class OpCode {
    EVENT,
    HELLO,
    INITIALIZE,
    HEARTBEAT;

    companion object Serializer : KSerializer<OpCode> {
        override val descriptor = buildClassSerialDescriptor("OpCode")

        override fun deserialize(decoder: Decoder) = OpCode.values()[decoder.decodeInt()]

        override fun serialize(encoder: Encoder, value: OpCode) {
            encoder.encodeInt(value.ordinal)
        }
    }
}

@Serializable
sealed interface Message {
    val op: OpCode

    private companion object Serializer : JsonContentPolymorphicSerializer<Message>(Message::class) {
        override fun selectDeserializer(element: JsonElement) = when (element.jsonObject["op"]?.jsonPrimitive?.int) {
            0 -> Event.serializer()
            1 -> Hello.serializer()
            2 -> Initialize.serializer()
            3 -> Heartbeat.serializer()
            else -> throw NoWhenBranchMatchedException()
        }
    }

    @Serializable
    data class Event(
        @SerialName("t")
        val type: Type,

        @SerialName("d")
        val data: Presence
    ) : Message {
        override val op = OpCode.EVENT

        enum class Type {
            INIT_STATE,
            PRESENCE_UPDATE
        }
    }

    @Serializable
    data class Hello(
        @SerialName("d")
        val data: Data
    ) : Message {
        override val op = OpCode.HELLO

        @Serializable
        data class Data(
            @SerialName("heartbeat_interval")
            val heartbeatInterval: Int
        )
    }

    @Serializable
    data class Initialize(
        @SerialName("d")
        val data: Data
    ) : Message {
        override val op = OpCode.INITIALIZE

        @Serializable
        data class Data(
            @SerialName("subscribe_to_id")
            val subscribeToId: String
        )
    }

    @Serializable
    class Heartbeat : Message {
        override val op = OpCode.HEARTBEAT
    }
}

object LanyardApi {
    private const val USER_ID = "289556910426816513"
    private const val WEBSOCKET_URL = "wss://api.lanyard.rest/socket"

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    private val httpClient = HttpClient(Js) {
        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(json)
        }

        install(ContentNegotiation) {
            json(json)
        }
    }

    private lateinit var webSocketSession: DefaultClientWebSocketSession
    var activity by mutableStateOf<Presence.Activity?>(null)

    suspend fun connectWebSocket() {
        try {
            webSocketSession = httpClient.webSocketSession(WEBSOCKET_URL)

            listenToSocket()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun listenToSocket() {
        webSocketSession.incoming.receiveAsFlow().collect { frame ->
            val message = when (frame) {
                is Frame.Text -> {
                    val jsonStr = frame.readText()

                    println("Received event message: $jsonStr")

                    json.decodeFromString<Message>(jsonStr)
                }
                else -> return@collect
            }

            when (message) {
                is Message.Event -> {
                    activity = message.data.activities.firstOrNull()
                }
                is Message.Hello -> {
                    val initializeMessage = Message.Initialize(Message.Initialize.Data(USER_ID))

                    webSocketSession.sendSerialized(initializeMessage)

                    coroutineScope {
                        launch(SupervisorJob() + Dispatchers.Main) {
                            runHeartbeat(message.data.heartbeatInterval.toLong())
                        }
                    }
                }
                else -> { }
            }
        }
    }

    private tailrec suspend fun runHeartbeat(interval: Long) {
        delay(interval)
        webSocketSession.sendSerialized(Message.Heartbeat())
        runHeartbeat(interval)
    }

    fun getAssetImage(applicationId: String, assetId: String): String {
        return "https://cdn.discordapp.com/app-assets/${applicationId}/${assetId}.png"
    }
}