package me.subhrajyoti.wordle.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.subhrajyoti.wordle.ui.components.GuessDistributionBar
import me.subhrajyoti.wordle.ui.components.NeonBottomNavItem
import me.subhrajyoti.wordle.ui.components.NeonGlowText
import me.subhrajyoti.wordle.ui.components.StatCard
import me.subhrajyoti.wordle.ui.theme.NeonTokyoColors

@Composable
fun CoreStatsScreen(
    modifier: Modifier = Modifier,
    userName: String = "OPERATOR_01",
    rank: String = "Ghost",
    version: String = "v2.0.48",
    gamesPlayed: Int = 142,
    winPercent: Int = 96,
    currentStreak: Int = 14,
    maxStreak: Int = 42,
    guessDistribution: Map<Int, Int> = mapOf(1 to 2, 2 to 18, 3 to 45, 4 to 32, 5 to 20, 6 to 5),
    onNavigateToPlay: () -> Unit = {},
    onNavigateToHistory: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeonTokyoColors.Background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatsHeader(userName = userName, rank = rank, version = version)
        Spacer(modifier = Modifier.height(16.dp))
        ModeSelector()
        Spacer(modifier = Modifier.height(24.dp))
        CoreStatsTitle()
        Spacer(modifier = Modifier.height(16.dp))
        StatsGrid(
            gamesPlayed = gamesPlayed,
            winPercent = winPercent,
            currentStreak = currentStreak,
            maxStreak = maxStreak
        )
        Spacer(modifier = Modifier.height(24.dp))
        GuessDistributionSection(guessDistribution = guessDistribution)
        Spacer(modifier = Modifier.height(24.dp))
        BottomNavigationStats(
            selectedTab = Tab.STATS,
            onTabSelected = { tab ->
                when (tab) {
                    Tab.PLAY -> onNavigateToPlay()
                    Tab.HISTORY -> onNavigateToHistory()
                    else -> {}
                }
            }
        )
    }
}

@Composable
private fun StatsHeader(userName: String, rank: String, version: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                contentDescription = "Menu",
                tint = NeonTokyoColors.OnSurfaceVariant
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            NeonGlowText(
                text = "NEON WORDLE",
                color = NeonTokyoColors.Primary,
                fontSize = 20,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = version,
                color = NeonTokyoColors.OnSurfaceVariant,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.BarChart,
                contentDescription = "Settings",
                tint = NeonTokyoColors.OnSurfaceVariant
            )
        }
    }
}

@Composable
private fun ModeSelector() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = NeonTokyoColors.SurfaceContainerLow,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ModeButton(text = "Infinite Mode", isSelected = true)
        ModeButton(text = "Daily Challenge", isSelected = false)
        ModeButton(text = "Archives", isSelected = false)
    }
}

@Composable
private fun ModeButton(text: String, isSelected: Boolean) {
    val backgroundColor = if (isSelected) NeonTokyoColors.PrimaryContainer else NeonTokyoColors.SurfaceContainerLow
    val textColor = if (isSelected) NeonTokyoColors.OnPrimaryContainer else NeonTokyoColors.OnSurfaceVariant

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
private fun CoreStatsTitle() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "OPERATOR_01",
            color = NeonTokyoColors.Secondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.titleMedium.copy(
                shadow = Shadow(
                    color = NeonTokyoColors.Secondary.copy(alpha = 0.6f),
                    blurRadius = 8f
                )
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Rank: 1",
            color = NeonTokyoColors.OnSurfaceVariant,
            fontSize = 12.sp,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Core Stats",
            color = NeonTokyoColors.Primary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.headlineMedium.copy(
                shadow = Shadow(
                    color = NeonTokyoColors.Primary.copy(alpha = 0.8f),
                    blurRadius = 12f
                )
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "OPERATIONAL DATA RETRIEVED",
            color = NeonTokyoColors.OnSurfaceVariant,
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
private fun StatsGrid(
    gamesPlayed: Int,
    winPercent: Int,
    currentStreak: Int,
    maxStreak: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatCard(
            label = "Games Played",
            value = "$gamesPlayed",
            modifier = Modifier.weight(1f)
        )
        StatCard(
            label = "Win %",
            value = "$winPercent",
            modifier = Modifier.weight(1f)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatCard(
            label = "Current Streak",
            value = "$currentStreak",
            modifier = Modifier.weight(1f)
        )
        StatCard(
            label = "Max Streak",
            value = "$maxStreak",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun GuessDistributionSection(guessDistribution: Map<Int, Int>) {
    val maxCount = guessDistribution.values.maxOrNull() ?: 1

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Guess Distribution",
            color = NeonTokyoColors.OnSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(12.dp))
        guessDistribution.entries.sortedBy { it.key }.forEach { (guess, count) ->
            GuessDistributionBar(
                guessNumber = guess,
                count = count,
                maxCount = maxCount,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun BottomNavigationStats(
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
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NeonBottomNavItem(
            icon = Icons.Default.Terminal,
            label = "Play",
            isSelected = selectedTab == Tab.PLAY,
            onClick = { onTabSelected(Tab.PLAY) }
        )
        NeonBottomNavItem(
            icon = Icons.Default.History,
            label = "History",
            isSelected = selectedTab == Tab.HISTORY,
            onClick = { onTabSelected(Tab.HISTORY) }
        )
        NeonBottomNavItem(
            icon = Icons.Default.Leaderboard,
            label = "Stats",
            isSelected = selectedTab == Tab.STATS,
            onClick = { onTabSelected(Tab.STATS) }
        )
    }
}

@Preview
@Composable
private fun CoreStatsScreenPreview() {
    CoreStatsScreen()
}