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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import me.subhrajyoti.wordle.ui.components.NeonBottomNavItem
import me.subhrajyoti.wordle.ui.components.NeonGlowText
import me.subhrajyoti.wordle.ui.components.SystemLogEntry
import me.subhrajyoti.wordle.ui.theme.NeonTokyoColors

data class LogEntry(
    val entryId: String,
    val word: String,
    val isWin: Boolean,
    val attempts: String,
    val date: String,
    val time: String,
    val solveTime: String? = null
)

@Composable
fun SystemLogScreen(
    modifier: Modifier = Modifier,
    userName: String = "OPERATOR_01",
    rank: String = "Ghost",
    version: String = "v2.0.48",
    totalEntries: Int = 124,
    lastSync: String = "14:02:44",
    onNavigateToPlay: () -> Unit = {},
    onNavigateToStats: () -> Unit = {}
) {
    val sampleLogs = listOf(
        LogEntry(
            entryId = "#4092",
            word = "CYBER",
            isWin = true,
            attempts = "4/6",
            date = "2023.10.24",
            time = "18:42:01",
            solveTime = null
        ),
        LogEntry(
            entryId = "#4091",
            word = "GHOST",
            isWin = false,
            attempts = "X/6",
            date = "2023.10.23",
            time = "22:15:09",
            solveTime = null
        ),
        LogEntry(
            entryId = "#4090",
            word = "NEON",
            isWin = true,
            attempts = "3/6",
            date = "2023.10.22",
            time = "09:05:11",
            solveTime = "timer 01m:12s"
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeonTokyoColors.Background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SystemLogHeader(userName = userName, rank = rank, version = version)
        Spacer(modifier = Modifier.height(16.dp))
        ModeSelector()
        Spacer(modifier = Modifier.height(24.dp))
        SystemLogTitle(totalEntries = totalEntries, lastSync = lastSync)
        Spacer(modifier = Modifier.height(16.dp))
        LogEntriesList(logs = sampleLogs)
        Spacer(modifier = Modifier.height(8.dp))
        ArchivalNotice()
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationHistory(
            selectedTab = Tab.HISTORY,
            onTabSelected = { tab ->
                when (tab) {
                    Tab.PLAY -> onNavigateToPlay()
                    Tab.STATS -> onNavigateToStats()
                    else -> {}
                }
            }
        )
    }
}

@Composable
private fun SystemLogHeader(userName: String, rank: String, version: String) {
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
        ModeTab(text = "Infinite Mode", isSelected = true)
        ModeTab(text = "Daily Challenge", isSelected = false)
        ModeTab(text = "Archives", isSelected = false)
    }
}

@Composable
private fun ModeTab(text: String, isSelected: Boolean) {
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
private fun SystemLogTitle(totalEntries: Int, lastSync: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "SYS_LOG",
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
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "CONNECTION SECURE :: QUERYING HISTORICAL DATA",
            color = NeonTokyoColors.Secondary,
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(
                text = "TOTAL_ENTRIES: ${totalEntries.toString().padStart(3, '0')}",
                color = NeonTokyoColors.OnSurfaceVariant,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "LAST_SYNC: $lastSync",
                color = NeonTokyoColors.OnSurfaceVariant,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Composable
private fun LogEntriesList(logs: List<LogEntry>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(logs) { log ->
            SystemLogEntry(
                entryId = log.entryId,
                word = log.word,
                status = if (log.isWin) "SOLVED" else "FAILED",
                date = log.date,
                time = log.time,
                isWin = log.isWin,
                attempts = log.attempts,
                solveTime = log.solveTime
            )
        }
        item {
            Text(
                text = "> END OF RECENT LOGS.",
                color = NeonTokyoColors.OnSurfaceVariant,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun ArchivalNotice() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = NeonTokyoColors.SurfaceContainerLow,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = "Fetching archival records...",
            color = NeonTokyoColors.OnSurfaceVariant,
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "[OK] 104 older records found.",
                color = NeonTokyoColors.Secondary,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Awaiting user input to decrypt older blocks.",
            color = NeonTokyoColors.OnSurfaceVariant,
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
private fun BottomNavigationHistory(
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
private fun SystemLogScreenPreview() {
    SystemLogScreen()
}