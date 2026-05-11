package me.subhrajyoti.wordle

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import me.subhrajyoti.wordle.screens.CoreStatsScreen
import me.subhrajyoti.wordle.screens.Screen
import me.subhrajyoti.wordle.screens.SystemLogScreen
import me.subhrajyoti.wordle.screens.TerminalScreen
import me.subhrajyoti.wordle.ui.theme.NeonTokyoTheme

@Composable
fun App() {
    NeonTokyoTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Terminal) }

        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "screen_transition",
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) { screen ->
            when (screen) {
                Screen.Terminal -> TerminalScreen(
                    onNavigateToStats = { currentScreen = Screen.CoreStats },
                    onNavigateToHistory = { currentScreen = Screen.SystemLog }
                )
                Screen.CoreStats -> CoreStatsScreen(
                    onNavigateToPlay = { currentScreen = Screen.Terminal },
                    onNavigateToHistory = { currentScreen = Screen.SystemLog }
                )
                Screen.SystemLog -> SystemLogScreen(
                    onNavigateToPlay = { currentScreen = Screen.Terminal },
                    onNavigateToStats = { currentScreen = Screen.CoreStats }
                )
            }
        }
    }
}