
import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import ui.component.*

@Composable
fun Content() {
    Row(
        style = {
            // justifyContent(JustifyContent.Center)
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
                    }
                ) {
                    Text("nick")
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
                        I'm a 20yo student studying Applied Computer Science who likes making random things. 
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
            }

            // Column {
            //     H1(
            //         attrs = {
            //             style {
            //                 fontFamily("Space Grotesk")
            //                 fontSize(2.cssRem)
            //             }
            //         }
            //     ) {
            //         Text("uses")
            //     }
            //
            //     @Composable
            //     fun ElementScope<HTMLUListElement>.Li(text: String) {
            //         Li {
            //             Text(text)
            //         }
            //     }
            //
            //     Ul(
            //         attrs = {
            //             style {
            //             }
            //         }
            //     ) {
            //         Li {
            //             Text("Hardware")
            //             Ul {
            //                 Li("Keyboard")
            //                 Li("Mouse")
            //             }
            //         }
            //
            //         Li {
            //             Text("Software")
            //             Ul {
            //                 Li("Browser")
            //                 Li("IDE")
            //                 Li("OS")
            //                 Li("Shell")
            //                 Li("Terminal")
            //                 Li("Text Editor")
            //                 Li("Desktop")
            //             }
            //         }
            //
            //         Li {
            //             Text("Languages")
            //             Ul {
            //                 Li("C")
            //                 Li("C++")
            //                 Li("Java")
            //                 Li("Kotlin")
            //                 Li("Python")
            //             }
            //         }
            //     }
            // }
        }
    }
}