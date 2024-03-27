package ui.component

import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.H3

@Composable
fun SocialChip(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    url: String
) {
    Container(
        onClick = { window.open(url) }
    ) {
        icon()

        H3 {
            label()
        }
    }
}