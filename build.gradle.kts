plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose)
    alias(libs.plugins.ktlint)
}

ktlint {
    version = libs.versions.ktlint.get()
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    // @OptIn(ExperimentalWasmDsl::class)
    // wasmJs {
    //     moduleName = "website"
    //     browser {
    //         commonWebpackConfig {
    //             outputFileName = "website.js"
    //         }
    //     }
    //     binaries.executable()
    // }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                // implementation(compose.ui)
                implementation(compose.html.core)
                // implementation(compose.material3)
                // implementation(libs.coil.compose)
                // implementation(libs.coil.network.ktor)
                // implementation(libs.ktor.client.cio)
                // implementation("io.ktor:ktor-client-core:3.0.0-wasm1")
            }
        }
    }
}

// compose.experimental {
//     web.application {}
// }