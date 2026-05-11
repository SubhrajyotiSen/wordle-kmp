package me.subhrajyoti.wordle.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.subhrajyoti.wordle.ui.components.TileState
import me.subhrajyoti.wordle.ui.theme.NeonTokyoColors

@Composable
fun TerminalScreen(
    modifier: Modifier = Modifier,
    onNavigateToStats: () -> Unit = {},
    onNavigateToHistory: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeonTokyoColors.Background)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TerminalHeader()
        Spacer(modifier = Modifier.height(16.dp))
        SystemMessageLabel()
        Spacer(modifier = Modifier.height(8.dp))
        WordleGrid()
        Spacer(modifier = Modifier.height(16.dp))
        AttemptsCounter()
        Spacer(modifier = Modifier.height(12.dp))
        NewGameButton()
        Spacer(modifier = Modifier.weight(1f))
        Keyboard()
        Spacer(modifier = Modifier.height(8.dp))
        BottomNavigation(
            selectedTab = Tab.PLAY,
            onTabSelected = { tab ->
                when (tab) {
                    Tab.STATS -> onNavigateToStats()
                    Tab.HISTORY -> onNavigateToHistory()
                    else -> {}
                }
            }
        )
    }
}

@Composable
private fun TerminalHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                contentDescription = "Menu",
                tint = NeonTokyoColors.OnSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "NEON WORDLE",
            color = NeonTokyoColors.Primary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            style = androidx.compose.ui.text.TextStyle(
                shadow = Shadow(
                    color = NeonTokyoColors.Primary.copy(alpha = 0.8f),
                    blurRadius = 12f
                )
            )
        )
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = NeonTokyoColors.OnSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun SystemMessageLabel() {
    Text(
        text = "SYSTEM MESSAGE",
        color = NeonTokyoColors.OnSurfaceVariant,
        fontSize = 10.sp,
        fontFamily = FontFamily.SansSerif,
        letterSpacing = 2.sp
    )
}

@Composable
private fun WordleGrid() {
    val rows = listOf(
        WordleRowData(letters = listOf('C', 'Y', 'B', 'E', 'R'), states = listOf(TileState.CORRECT, TileState.PRESENT, TileState.CORRECT, TileState.ABSENT, TileState.PRESENT)),
        WordleRowData(letters = listOf('C', 'A', 'B', 'L', 'E'), states = listOf(TileState.PRESENT, TileState.CORRECT, TileState.ABSENT, TileState.PRESENT, TileState.CORRECT)),
        WordleRowData(letters = listOf('C', 'A'), states = listOf(TileState.FOCUSED, TileState.FOCUSED)),
        WordleRowData(letters = emptyList(), states = emptyList()),
        WordleRowData(letters = emptyList(), states = emptyList()),
        WordleRowData(letters = emptyList(), states = emptyList())
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        rows.forEach { rowData ->
            WordleRow(rowData = rowData)
        }
    }
}

private data class WordleRowData(
    val letters: List<Char>,
    val states: List<TileState>
)

@Composable
private fun WordleRow(rowData: WordleRowData) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        repeat(5) { index ->
            val letter = rowData.letters.getOrNull(index) ?: ' '
            val state = rowData.states.getOrNull(index) ?: TileState.EMPTY
            WordleTile(letter = letter, state = state)
        }
    }
}

@Composable
private fun WordleTile(letter: Char, state: TileState) {
    val backgroundColor = when (state) {
        TileState.CORRECT -> NeonTokyoColors.Secondary.copy(alpha = 0.3f)
        TileState.PRESENT -> NeonTokyoColors.Tertiary.copy(alpha = 0.3f)
        TileState.ABSENT -> NeonTokyoColors.SurfaceContainerHigh
        TileState.EMPTY -> NeonTokyoColors.SurfaceContainerLowest
        TileState.FOCUSED -> NeonTokyoColors.SurfaceContainer
    }

    val borderColor = when (state) {
        TileState.CORRECT -> NeonTokyoColors.Secondary
        TileState.PRESENT -> NeonTokyoColors.Tertiary
        TileState.ABSENT -> NeonTokyoColors.Outline.copy(alpha = 0.3f)
        TileState.EMPTY -> NeonTokyoColors.Outline.copy(alpha = 0.15f)
        TileState.FOCUSED -> NeonTokyoColors.Primary
    }

    val textColor = when (state) {
        TileState.CORRECT -> NeonTokyoColors.Secondary
        TileState.PRESENT -> NeonTokyoColors.Tertiary
        TileState.ABSENT -> NeonTokyoColors.OnSurfaceVariant.copy(alpha = 0.5f)
        TileState.EMPTY -> NeonTokyoColors.OnSurfaceVariant.copy(alpha = 0.3f)
        TileState.FOCUSED -> NeonTokyoColors.Primary
    }

    val borderWidth = when (state) {
        TileState.FOCUSED -> 2.dp
        else -> 1.dp
    }

    val glowColor = when (state) {
        TileState.CORRECT -> NeonTokyoColors.Secondary.copy(alpha = 0.6f)
        TileState.PRESENT -> NeonTokyoColors.Tertiary.copy(alpha = 0.6f)
        TileState.FOCUSED -> NeonTokyoColors.Primary.copy(alpha = 0.6f)
        else -> null
    }

    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .then(
                if (glowColor != null) {
                    Modifier.border(
                        width = borderWidth,
                        color = borderColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                } else {
                    Modifier.border(
                        width = borderWidth,
                        color = borderColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (letter != ' ') {
            Text(
                text = letter.toString(),
                color = textColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                style = if (glowColor != null) {
                    androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = glowColor,
                            blurRadius = 8f
                        )
                    )
                } else {
                    androidx.compose.ui.text.TextStyle()
                }
            )
        }
    }
}

@Composable
private fun AttemptsCounter() {
    Text(
        text = "ATTEMPTS: 2/6",
        color = NeonTokyoColors.Secondary,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun NewGameButton() {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(44.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(NeonTokyoColors.SurfaceContainer)
            .border(
                width = 1.dp,
                color = NeonTokyoColors.Primary.copy(alpha = 0.5f),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = {}),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "New Game",
            color = NeonTokyoColors.Primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif,
            style = androidx.compose.ui.text.TextStyle(
                shadow = Shadow(
                    color = NeonTokyoColors.Primary.copy(alpha = 0.6f),
                    blurRadius = 8f
                )
            )
        )
    }
}

@Composable
private fun Keyboard() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        KeyboardRow(keys = listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"))
        KeyboardRow(keys = listOf("A", "S", "D", "F", "G", "H", "J", "K", "L"), horizontalPadding = 12.dp)
        KeyboardRow(
            keys = listOf("ENTER", "Z", "X", "C", "V", "B", "N", "M", "backspace"),
            specialKeys = setOf("ENTER", "backspace"),
            horizontalPadding = 8.dp
        )
    }
}

@Composable
private fun KeyboardRow(
    keys: List<String>,
    specialKeys: Set<String> = emptySet(),
    horizontalPadding: androidx.compose.ui.unit.Dp = 0.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        keys.forEach { key ->
            val isSpecial = key in specialKeys
            val keyWeight = if (isSpecial) 1.5f else 1f

            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .height(48.dp)
                    .weight(keyWeight)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        when {
                            key == "ENTER" -> NeonTokyoColors.Secondary.copy(alpha = 0.2f)
                            key == "backspace" -> NeonTokyoColors.Primary.copy(alpha = 0.2f)
                            else -> NeonTokyoColors.SurfaceContainer
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = NeonTokyoColors.Outline.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable(onClick = {}),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (key == "backspace") "⌫" else key,
                    color = when {
                        key == "ENTER" -> NeonTokyoColors.Secondary
                        key == "backspace" -> NeonTokyoColors.Primary
                        else -> NeonTokyoColors.OnSurface
                    },
                    fontSize = if (isSpecial) 14.sp else 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}

enum class Tab {
    PLAY, HISTORY, STATS
}

@Composable
private fun BottomNavigation(
    selectedTab: Tab,
    onTabSelected: (Tab) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = NeonTokyoColors.SurfaceContainerLow,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            icon = Icons.Default.Terminal,
            label = "Play",
            isSelected = selectedTab == Tab.PLAY,
            onClick = { onTabSelected(Tab.PLAY) }
        )
        BottomNavItem(
            icon = Icons.Default.History,
            label = "History",
            isSelected = selectedTab == Tab.HISTORY,
            onClick = { onTabSelected(Tab.HISTORY) }
        )
        BottomNavItem(
            icon = Icons.Default.Leaderboard,
            label = "Stats",
            isSelected = selectedTab == Tab.STATS,
            onClick = { onTabSelected(Tab.STATS) }
        )
    }
}

@Composable
private fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) NeonTokyoColors.Primary else NeonTokyoColors.OnSurfaceVariant

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = color,
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Preview
@Composable
private fun TerminalScreenPreview() {
    TerminalScreen()
}