package me.subhrajyoti.wordle

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Wordle Unlimited",
    ) {
        App()
    }
}