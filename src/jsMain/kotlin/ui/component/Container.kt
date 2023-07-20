package ui.component

import androidx.compose.runtime.Composable
import androidx.compose.web.events.SyntheticMouseEvent
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import ui.theme.foregroundColor

@Composable
fun Container(
    gap: CSSNumeric = 0.6.cssRem,
    onClick: ((SyntheticMouseEvent) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                alignItems(AlignItems.Center)
                justifyContent(JustifyContent.Center)
                gap(gap)
                padding(1.2.cssRem)
                color(Color("#1B4B3A"))
                backgroundColor(foregroundColor)
                borderRadius(1.4.cssRem)
                property("width", "fit-content")

                if (onClick != null) cursor("pointer")
            }

            if (onClick != null) onClick(onClick)
        }
    ) {
        content()
    }
}