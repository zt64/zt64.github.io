import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("org.jetbrains.compose") version "1.2.0-beta01"
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.web.core)

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                // Ktor
                val ktorVersion = "2.1.1"
                implementation("io.ktor:ktor-client-core-js:$ktorVersion")
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-websockets-js:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
    }
}