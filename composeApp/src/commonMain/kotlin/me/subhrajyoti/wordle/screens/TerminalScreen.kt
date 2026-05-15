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
    rows: List<WordleRowData> = defaultRows,
    attempts: Int = 2,
    maxAttempts: Int = 6,
    keyStates: Map<Char, KeyState> = defaultKeyStates,
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
        WordleGrid(rows = rows)
        Spacer(modifier = Modifier.height(16.dp))
        AttemptsCounter(attempts = attempts, maxAttempts = maxAttempts)
        Spacer(modifier = Modifier.height(12.dp))
        NewGameButton()
        Spacer(modifier = Modifier.height(16.dp))
        Keyboard(keyStates = keyStates)
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

private val defaultRows = listOf(
    WordleRowData(letters = listOf('C', 'Y', 'B', 'E', 'R'), states = listOf(TileState.CORRECT, TileState.PRESENT, TileState.CORRECT, TileState.ABSENT, TileState.PRESENT)),
    WordleRowData(letters = listOf('C', 'A', 'B', 'L', 'E'), states = listOf(TileState.PRESENT, TileState.CORRECT, TileState.ABSENT, TileState.PRESENT, TileState.CORRECT)),
    WordleRowData(letters = listOf('C', 'A'), states = listOf(TileState.FOCUSED, TileState.FOCUSED)),
    WordleRowData(),
    WordleRowData(),
    WordleRowData()
)

private val defaultKeyStates = mapOf(
    'C' to KeyState.CORRECT,
    'Y' to KeyState.PRESENT,
    'B' to KeyState.CORRECT,
    'E' to KeyState.ABSENT,
    'R' to KeyState.PRESENT,
    'A' to KeyState.PRESENT,
    'L' to KeyState.CORRECT,
    'D' to KeyState.ABSENT,
    'F' to KeyState.ABSENT,
    'G' to KeyState.ABSENT,
    'H' to KeyState.ABSENT,
    'I' to KeyState.ABSENT,
    'K' to KeyState.ABSENT,
    'M' to KeyState.ABSENT,
    'N' to KeyState.ABSENT,
    'O' to KeyState.ABSENT,
    'P' to KeyState.ABSENT,
    'Q' to KeyState.ABSENT,
    'S' to KeyState.ABSENT,
    'T' to KeyState.ABSENT,
    'U' to KeyState.ABSENT,
    'V' to KeyState.ABSENT,
    'W' to KeyState.ABSENT,
    'X' to KeyState.ABSENT,
    'Z' to KeyState.ABSENT
)

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
private fun WordleGrid(rows: List<WordleRowData> = defaultRows) {
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

data class WordleRowData(
    val letters: List<Char> = emptyList(),
    val states: List<TileState> = emptyList()
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
private fun AttemptsCounter(attempts: Int = 2, maxAttempts: Int = 6) {
    Text(
        text = "ATTEMPTS: $attempts/$maxAttempts",
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
private fun Keyboard(
    keyStates: Map<Char, KeyState> = emptyMap()
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        KeyboardRow(
            keys = listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
            keyStates = keyStates
        )
        KeyboardRow(
            keys = listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
            keyStates = keyStates,
            horizontalPadding = 12.dp
        )
        KeyboardRow(
            keys = listOf('Z', 'X', 'C', 'V', 'B', 'N', 'M'),
            keyStates = keyStates,
            showEnter = true,
            showBackspace = true,
            horizontalPadding = 8.dp
        )
    }
}

@Composable
private fun KeyboardRow(
    keys: List<Char>,
    keyStates: Map<Char, KeyState> = emptyMap(),
    showEnter: Boolean = false,
    showBackspace: Boolean = false,
    horizontalPadding: androidx.compose.ui.unit.Dp = 0.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showEnter) {
            SpecialKey(
                label = "Enter",
                type = SpecialKeyType.ENTER,
                onClick = {}
            )
        }
        keys.forEach { key ->
            val state = keyStates[key] ?: KeyState.DEFAULT
            KeyboardKey(
                letter = key,
                state = state,
                onClick = {}
            )
        }
        if (showBackspace) {
            SpecialKey(
                label = "⌫",
                type = SpecialKeyType.BACKSPACE,
                onClick = {}
            )
        }
    }
}

@Composable
private fun KeyboardKey(
    letter: Char,
    state: KeyState,
    onClick: () -> Unit
) {
    val (backgroundColor, textColor, borderColor) = when (state) {
        KeyState.DEFAULT -> Triple(
            NeonTokyoColors.SurfaceContainer,
            NeonTokyoColors.OnSurface,
            NeonTokyoColors.Outline.copy(alpha = 0.3f)
        )
        KeyState.CORRECT -> Triple(
            NeonTokyoColors.Secondary.copy(alpha = 0.4f),
            NeonTokyoColors.Secondary,
            NeonTokyoColors.Secondary
        )
        KeyState.PRESENT -> Triple(
            NeonTokyoColors.Tertiary.copy(alpha = 0.4f),
            NeonTokyoColors.Tertiary,
            NeonTokyoColors.Tertiary
        )
        KeyState.ABSENT -> Triple(
            NeonTokyoColors.SurfaceContainerHigh,
            NeonTokyoColors.OnSurfaceVariant.copy(alpha = 0.4f),
            NeonTokyoColors.Outline.copy(alpha = 0.15f)
        )
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .width(28.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
private fun SpecialKey(
    label: String,
    type: SpecialKeyType,
    onClick: () -> Unit
) {
    val (backgroundColor, textColor) = when (type) {
        SpecialKeyType.ENTER -> Pair(NeonTokyoColors.Secondary.copy(alpha = 0.2f), NeonTokyoColors.Secondary)
        SpecialKeyType.BACKSPACE -> Pair(NeonTokyoColors.Primary.copy(alpha = 0.2f), NeonTokyoColors.Primary)
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .width(52.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = NeonTokyoColors.Outline.copy(alpha = 0.3f),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
    }
}

enum class KeyState {
    DEFAULT,
    CORRECT,
    PRESENT,
    ABSENT
}

enum class SpecialKeyType {
    ENTER,
    BACKSPACE
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

@Preview
@Composable
private fun TerminalScreenFreshGamePreview() {
    TerminalScreen(
        rows = listOf(
            WordleRowData(),
            WordleRowData(),
            WordleRowData(),
            WordleRowData(),
            WordleRowData(),
            WordleRowData()
        ),
        attempts = 0,
        keyStates = emptyMap()
    )
}

@Preview
@Composable
private fun TerminalScreenMidGamePreview() {
    TerminalScreen(
        rows = listOf(
            WordleRowData(letters = listOf('S', 'T', 'A', 'R', 'E'), states = listOf(TileState.ABSENT, TileState.PRESENT, TileState.CORRECT, TileState.ABSENT, TileState.PRESENT)),
            WordleRowData(letters = listOf('C', 'R', 'A', 'N', 'E'), states = listOf(TileState.PRESENT, TileState.CORRECT, TileState.CORRECT, TileState.ABSENT, TileState.CORRECT)),
            WordleRowData(letters = listOf('P', 'L', 'A', 'N', 'T'), states = listOf(TileState.FOCUSED, TileState.FOCUSED, TileState.CORRECT, TileState.FOCUSED, TileState.CORRECT)),
            WordleRowData(),
            WordleRowData(),
            WordleRowData()
        ),
        attempts = 3,
        keyStates = mapOf(
            'S' to KeyState.ABSENT,
            'T' to KeyState.PRESENT,
            'A' to KeyState.CORRECT,
            'R' to KeyState.ABSENT,
            'E' to KeyState.CORRECT,
            'C' to KeyState.PRESENT,
            'N' to KeyState.ABSENT
        )
    )
}

@Preview
@Composable
private fun TerminalScreenWonPreview() {
    TerminalScreen(
        rows = listOf(
            WordleRowData(letters = listOf('G', 'U', 'E', 'S', 'T'), states = listOf(TileState.ABSENT, TileState.ABSENT, TileState.PRESENT, TileState.ABSENT, TileState.PRESENT)),
            WordleRowData(letters = listOf('B', 'R', 'E', 'A', 'K'), states = listOf(TileState.PRESENT, TileState.CORRECT, TileState.CORRECT, TileState.CORRECT, TileState.ABSENT)),
            WordleRowData(letters = listOf('D', 'R', 'E', 'A', 'M'), states = listOf(TileState.ABSENT, TileState.CORRECT, TileState.CORRECT, TileState.CORRECT, TileState.PRESENT)),
            WordleRowData(letters = listOf('C', 'R', 'E', 'A', 'M'), states = listOf(TileState.CORRECT, TileState.CORRECT, TileState.CORRECT, TileState.CORRECT, TileState.CORRECT)),
            WordleRowData(),
            WordleRowData()
        ),
        attempts = 4,
        keyStates = mapOf(
            'G' to KeyState.ABSENT,
            'U' to KeyState.ABSENT,
            'E' to KeyState.CORRECT,
            'S' to KeyState.ABSENT,
            'T' to KeyState.PRESENT,
            'B' to KeyState.PRESENT,
            'R' to KeyState.CORRECT,
            'A' to KeyState.CORRECT,
            'K' to KeyState.ABSENT,
            'D' to KeyState.ABSENT,
            'M' to KeyState.PRESENT,
            'C' to KeyState.CORRECT
        )
    )
}

@Preview
@Composable
private fun TerminalScreenLostPreview() {
    TerminalScreen(
        rows = listOf(
            WordleRowData(letters = listOf('S', 'L', 'A', 'T', 'E'), states = listOf(TileState.ABSENT, TileState.ABSENT, TileState.PRESENT, TileState.ABSENT, TileState.ABSENT)),
            WordleRowData(letters = listOf('C', 'R', 'O', 'W', 'D'), states = listOf(TileState.ABSENT, TileState.PRESENT, TileState.ABSENT, TileState.ABSENT, TileState.ABSENT)),
            WordleRowData(letters = listOf('P', 'R', 'U', 'N', 'G'), states = listOf(TileState.ABSENT, TileState.PRESENT, TileState.ABSENT, TileState.ABSENT, TileState.ABSENT)),
            WordleRowData(letters = listOf('F', 'R', 'Y', 'I', 'N'), states = listOf(TileState.ABSENT, TileState.PRESENT, TileState.ABSENT, TileState.ABSENT, TileState.ABSENT)),
            WordleRowData(letters = listOf('W', 'R', 'O', 'T', 'H'), states = listOf(TileState.ABSENT, TileState.PRESENT, TileState.ABSENT, TileState.ABSENT, TileState.ABSENT)),
            WordleRowData(letters = listOf('B', 'R', 'I', 'M', 'S'), states = listOf(TileState.ABSENT, TileState.PRESENT, TileState.ABSENT, TileState.ABSENT, TileState.ABSENT))
        ),
        attempts = 6,
        keyStates = mapOf(
            'S' to KeyState.ABSENT,
            'L' to KeyState.ABSENT,
            'A' to KeyState.PRESENT,
            'T' to KeyState.ABSENT,
            'E' to KeyState.ABSENT,
            'C' to KeyState.ABSENT,
            'R' to KeyState.PRESENT,
            'O' to KeyState.ABSENT,
            'W' to KeyState.ABSENT,
            'D' to KeyState.ABSENT,
            'P' to KeyState.ABSENT,
            'U' to KeyState.ABSENT,
            'N' to KeyState.ABSENT,
            'G' to KeyState.ABSENT,
            'F' to KeyState.ABSENT,
            'Y' to KeyState.ABSENT,
            'I' to KeyState.ABSENT,
            'B' to KeyState.ABSENT,
            'M' to KeyState.ABSENT,
            'H' to KeyState.ABSENT,
            'K' to KeyState.ABSENT,
            'J' to KeyState.ABSENT,
            'Q' to KeyState.ABSENT,
            'V' to KeyState.ABSENT,
            'X' to KeyState.ABSENT,
            'Z' to KeyState.ABSENT
        )
    )
}

@Preview
@Composable
private fun KeyboardDefaultPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(NeonTokyoColors.Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Keyboard — Default State",
            color = NeonTokyoColors.OnSurface,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Keyboard(keyStates = emptyMap())
    }
}

@Preview
@Composable
private fun KeyboardWithStatesPreview() {
    val keyStates = mapOf(
        'C' to KeyState.CORRECT,
        'Y' to KeyState.PRESENT,
        'B' to KeyState.CORRECT,
        'E' to KeyState.ABSENT,
        'R' to KeyState.PRESENT,
        'A' to KeyState.PRESENT,
        'L' to KeyState.CORRECT,
        'D' to KeyState.ABSENT,
        'F' to KeyState.ABSENT,
        'G' to KeyState.ABSENT,
        'H' to KeyState.ABSENT,
        'I' to KeyState.ABSENT,
        'K' to KeyState.ABSENT,
        'M' to KeyState.ABSENT,
        'N' to KeyState.ABSENT,
        'O' to KeyState.ABSENT,
        'P' to KeyState.ABSENT,
        'Q' to KeyState.ABSENT,
        'S' to KeyState.ABSENT,
        'T' to KeyState.ABSENT,
        'U' to KeyState.ABSENT,
        'V' to KeyState.ABSENT,
        'W' to KeyState.ABSENT,
        'X' to KeyState.ABSENT,
        'Z' to KeyState.ABSENT
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(NeonTokyoColors.Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Keyboard — After Guesses",
            color = NeonTokyoColors.OnSurface,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Keyboard(keyStates = keyStates)
    }
}