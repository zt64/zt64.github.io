package ui.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.dom.Div
import ui.theme.WebsiteStyleSheet

@Composable
fun List(
    style: (StyleScope.() -> Unit)? = null,
    gap: CSSNumeric? = null,
    content: @Composable () -> Unit
) {
    Div(
        attrs = {
            classes(WebsiteStyleSheet.container)

            style {
                if (gap != null) gap(gap)
            }

            if (style != null) style(style)
        }
    ) {
        content()
    }
}