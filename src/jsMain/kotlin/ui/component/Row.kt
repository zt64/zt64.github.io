package ui.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Composable
fun Row(
    style: (StyleScope.() -> Unit)? = null,
    gap: CSSNumeric? = null,
    content: @Composable () -> Unit
) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)

                if (gap != null) gap(gap)
            }

            if (style != null) style(style)
        }
    ) {
        content()
    }
}