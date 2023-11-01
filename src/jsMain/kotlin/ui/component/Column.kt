package ui.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun Column(
    attrs: (AttrsScope<HTMLDivElement>.() -> Unit)? = null,
    gap: CSSNumeric? = null,
    content: ContentBuilder<HTMLDivElement>
) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)

                if (gap != null) gap(gap)
            }

            if (attrs != null) attrs()
        },
        content = content
    )
}
