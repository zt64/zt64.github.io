import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposableInBody
import ui.theme.WebsiteStyleSheet

fun main() {
    renderComposableInBody {
        Style(WebsiteStyleSheet)

        Content()
    }
}