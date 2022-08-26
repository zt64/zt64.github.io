import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposableInBody
import ui.component.*
import ui.theme.WebsiteStyleSheet
import kotlin.random.Random

fun main() {

    renderComposableInBody {
	    var zoter by remember { mutableStateOf(false) }
        Style(WebsiteStyleSheet)

        Row(
            style = {
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
                height(100.vh)
            },
            gap = 6.cssRem
        ) {
            Img(
                attrs = {
                    style {
                        width(30.cssRem)
                        height(30.cssRem)
                        borderRadius(5.cssRem)
                    }
                },
                src = "https://avatars.githubusercontent.com/u/31907977?v=4"
            )

            Column(
                gap = 1.1.cssRem
            ) {
                H1(
                    attrs = {
                        style {
                            fontFamily("Space Grotesk")
                            fontSize(6.cssRem)
                        }
                    }
                ) {
                    Text("hey, i'm ")

                    Span(
                        attrs = {
                            style {
                                fontWeight(700)
                            }
                            onClick {
                                if (zoter) {
                                    zoter = false;
                                    return@onClick
                                }
                                zoter = Random.nextInt(10) < 3
                            }
                        }
                    ) {
                        if (zoter) {
                            Text("zoter")
                        } else {
                            Text("zt")
                        }
                    }
                }

                H3(
                    attrs = {
                        style {
                            color(Color("#E8E8E8"))
                            width(30.cssRem)
                        }
                    }
                ) {
                    Text(
                        """
                        I'm an 18yo student studying Applied Computer Science who likes making random things. 
                        Currently I’m working on a YouTube client for Android called Hyperion.
                        """.trimIndent()
                    )
                }

                Row(
                    gap = 1.2.cssRem
                ) {
                    SocialChip(
                        icon = { Icon("icons/github.svg") },
                        label = { Text("Github") },
                        url = "https://github.com/zt64"
                    )

                    SocialChip(
                        icon = { Icon("icons/discord.svg") },
                        label = { Text("Discord") },
                        url = ""
                    )
                }

                LanyardCard()
            }
        }
    }
}
