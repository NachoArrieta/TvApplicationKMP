package nacho.tv.application.kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TvApplicationKMP",
    ) {
        App()
    }
}