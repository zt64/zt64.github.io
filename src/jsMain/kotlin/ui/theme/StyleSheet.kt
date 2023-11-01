package ui.theme

import org.jetbrains.compose.web.css.*

object WebsiteStyleSheet : StyleSheet() {
    init {
        "body" style {
            display(DisplayStyle.Flex)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            margin(0.px)
            letterSpacing(0.01.cssRem)

            color(foregroundColor)
            backgroundColor(backgroundColor)
        }

        "h1, h2, h3, h4, h5, h6" style {
            fontFamily("JetBrains Mono")
            margin(0.px)
        }
    }

    val container by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Row)

        media(mediaMaxWidth(72.cssRem)) {
            self style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
            }
        }
    }
}
