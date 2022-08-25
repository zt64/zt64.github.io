package ui.component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Img

@Composable
fun Icon(
    path: String
) {
    Img(
        attrs = {
            style {
                width(2.cssRem)
                height(2.cssRem)
            }
        },
        src = path
    )
}