package ui.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement
import ui.theme.WebsiteStyleSheet

@Composable
fun Row(
    style: (StyleScope.() -> Unit)? = null,
    gap: CSSNumeric? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    Div(
        attrs = {
            classes(WebsiteStyleSheet.container)

            style {
                if (gap != null) gap(gap)
            }

            if (style != null) style(style)
        },
        content = content
    )
}