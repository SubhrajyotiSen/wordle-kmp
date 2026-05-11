package me.subhrajyoti.wordle.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.subhrajyoti.wordle.ui.theme.NeonTokyoColors

@Preview
@Composable
fun NeonGlowTextPreview() {
    Column(
        modifier = Modifier.background(NeonTokyoColors.Background).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NeonGlowText(
            text = "NEON WORDLE",
            color = NeonTokyoColors.Primary,
            fontSize = 24,
            fontWeight = FontWeight.Bold
        )
        NeonGlowText(
            text = "CYBER",
            color = NeonTokyoColors.Secondary,
            fontSize = 20,
            fontWeight = FontWeight.Bold
        )
        NeonGlowText(
            text = "GHOST",
            color = NeonTokyoColors.Tertiary,
            fontSize = 18,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun NeonCardPreview() {
    NeonCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Sample Card Content",
            color = NeonTokyoColors.OnSurface
        )
    }
}

@Preview
@Composable
fun NeonButtonPreview() {
    Column(
        modifier = Modifier.background(NeonTokyoColors.Background).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NeonButton(text = "Enabled Button", onClick = {})
        NeonButton(text = "Disabled Button", onClick = {}, enabled = false)
    }
}

@Preview
@Composable
fun NeonKeyboardKeyPreview() {
    Row(
        modifier = Modifier.background(NeonTokyoColors.Background).padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        listOf("Q", "W", "E", "R", "T", "Y").forEach { letter ->
            NeonKeyboardKey(letter = letter, onClick = {})
        }
    }
}

@Preview
@Composable
fun NeonWordleTilePreview() {
    Column(
        modifier = Modifier.background(NeonTokyoColors.Background).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val letters = listOf('C', 'Y', 'B', 'E', 'R')
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TileState.entries.take(3).forEachIndexed { index, state ->
                NeonWordleTile(
                    letter = letters[index],
                    state = state
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TileState.entries.drop(3).forEachIndexed { index, state ->
                NeonWordleTile(
                    letter = letters[index + 3],
                    state = state
                )
            }
        }
    }
}

@Preview
@Composable
fun NeonBottomNavItemPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(NeonTokyoColors.SurfaceContainerLow)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NeonBottomNavItem(
            icon = Icons.Default.Terminal,
            label = "Play",
            isSelected = true,
            onClick = {}
        )
        NeonBottomNavItem(
            icon = Icons.Default.History,
            label = "History",
            isSelected = false,
            onClick = {}
        )
        NeonBottomNavItem(
            icon = Icons.Default.Leaderboard,
            label = "Stats",
            isSelected = false,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun StatCardPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(NeonTokyoColors.Background)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatCard(
            label = "Games Played",
            value = "142",
            modifier = Modifier.weight(1f)
        )
        StatCard(
            label = "Win %",
            value = "96",
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun GuessDistributionBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(NeonTokyoColors.Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GuessDistributionBar(guessNumber = 1, count = 2, maxCount = 45)
        GuessDistributionBar(guessNumber = 2, count = 18, maxCount = 45)
        GuessDistributionBar(guessNumber = 3, count = 45, maxCount = 45)
        GuessDistributionBar(guessNumber = 4, count = 32, maxCount = 45)
        GuessDistributionBar(guessNumber = 5, count = 20, maxCount = 45)
        GuessDistributionBar(guessNumber = 6, count = 5, maxCount = 45)
    }
}

@Preview
@Composable
fun SystemLogEntryPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(NeonTokyoColors.Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SystemLogEntry(
            entryId = "#4092",
            word = "CYBER",
            status = "SOLVED",
            date = "2023.10.24",
            time = "18:42:01",
            isWin = true,
            attempts = "4/6"
        )
        SystemLogEntry(
            entryId = "#4091",
            word = "GHOST",
            status = "FAILED",
            date = "2023.10.23",
            time = "22:15:09",
            isWin = false,
            attempts = "X/6"
        )
        SystemLogEntry(
            entryId = "#4090",
            word = "NEON",
            status = "SOLVED",
            date = "2023.10.22",
            time = "09:05:11",
            isWin = true,
            attempts = "3/6",
            solveTime = "timer 01m:12s"
        )
    }
}