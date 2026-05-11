package me.subhrajyoti.wordle.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.subhrajyoti.wordle.ui.theme.NeonTokyoColors

@Composable
fun NeonGlowText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = NeonTokyoColors.Primary,
    fontSize: Int = 24,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        fontFamily = FontFamily.SansSerif,
        style = MaterialTheme.typography.headlineMedium.copy(
            shadow = androidx.compose.ui.graphics.Shadow(
                color = color.copy(alpha = 0.8f),
                blurRadius = 12f
            )
        )
    )
}

@Composable
fun NeonCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = NeonTokyoColors.SurfaceContainer,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = NeonTokyoColors.Primary.copy(alpha = 0.3f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp)
    ) {
        content()
    }
}

@Composable
fun NeonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val glowColor = if (enabled) NeonTokyoColors.Primary else NeonTokyoColors.Primary.copy(alpha = 0.5f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(NeonTokyoColors.SurfaceContainer)
            .border(
                width = 1.dp,
                color = glowColor.copy(alpha = if (enabled) 0.5f else 0.2f),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (enabled) NeonTokyoColors.Primary else NeonTokyoColors.Primary.copy(alpha = 0.5f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun NeonKeyboardKey(
    letter: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = NeonTokyoColors.SurfaceContainer,
    textColor: Color = NeonTokyoColors.OnSurface
) {
    Box(
        modifier = modifier
            .size(width = 32.dp, height = 48.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = NeonTokyoColors.Outline.copy(alpha = 0.5f),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun NeonWordleTile(
    letter: Char,
    state: TileState,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (state) {
        TileState.CORRECT -> NeonTokyoColors.Secondary
        TileState.PRESENT -> NeonTokyoColors.Tertiary
        TileState.ABSENT -> NeonTokyoColors.SurfaceContainerHigh
        TileState.EMPTY -> NeonTokyoColors.SurfaceContainerLow
        TileState.FOCUSED -> NeonTokyoColors.SurfaceContainer
    }

    val textColor = when (state) {
        TileState.CORRECT -> NeonTokyoColors.OnSecondary
        TileState.PRESENT -> NeonTokyoColors.OnTertiary
        TileState.ABSENT -> NeonTokyoColors.OnSurfaceVariant
        TileState.EMPTY -> NeonTokyoColors.OnSurfaceVariant
        TileState.FOCUSED -> NeonTokyoColors.OnSurface
    }

    val borderColor = when (state) {
        TileState.FOCUSED -> NeonTokyoColors.Primary
        else -> NeonTokyoColors.Outline.copy(alpha = 0.3f)
    }

    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .border(
                width = if (state == TileState.FOCUSED) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            color = textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
    }
}

enum class TileState {
    EMPTY,
    FOCUSED,
    CORRECT,
    PRESENT,
    ABSENT
}

@Composable
fun NeonBottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = if (isSelected) NeonTokyoColors.Primary else NeonTokyoColors.OnSurfaceVariant

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = color,
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun StatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = NeonTokyoColors.SurfaceContainerLow,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = NeonTokyoColors.Primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.headlineLarge.copy(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = NeonTokyoColors.Primary.copy(alpha = 0.6f),
                    blurRadius = 8f
                )
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = NeonTokyoColors.OnSurfaceVariant,
            fontSize = 12.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun GuessDistributionBar(
    guessNumber: Int,
    count: Int,
    maxCount: Int,
    modifier: Modifier = Modifier
) {
    val fraction = if (maxCount > 0) count.toFloat() / maxCount.toFloat() else 0f

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$guessNumber",
            color = NeonTokyoColors.OnSurfaceVariant,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.width(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(20.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(NeonTokyoColors.SurfaceContainerHigh)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction)
                    .height(20.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                NeonTokyoColors.Secondary.copy(alpha = 0.8f),
                                NeonTokyoColors.Secondary
                            )
                        )
                    )
            )
        }
    }
}

@Composable
fun SystemLogEntry(
    entryId: String,
    word: String,
    status: String,
    date: String,
    time: String,
    isWin: Boolean,
    attempts: String? = null,
    solveTime: String? = null,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isWin) NeonTokyoColors.Secondary else NeonTokyoColors.Tertiary
    val statusText = if (isWin) "SOLVED" else "FAILED"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = NeonTokyoColors.SurfaceContainerLow,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor.copy(alpha = 0.3f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Entry_ID: $entryId",
                    color = NeonTokyoColors.OnSurfaceVariant,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = date,
                    color = NeonTokyoColors.OnSurfaceVariant,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = word,
                    color = NeonTokyoColors.Primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = NeonTokyoColors.Primary.copy(alpha = 0.6f),
                            blurRadius = 8f
                        )
                    )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "$statusText: $attempts",
                    color = if (isWin) NeonTokyoColors.Secondary else NeonTokyoColors.Tertiary,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            if (solveTime != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = solveTime,
                    color = NeonTokyoColors.OnSurfaceVariant,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = time,
                color = NeonTokyoColors.OnSurfaceVariant,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}