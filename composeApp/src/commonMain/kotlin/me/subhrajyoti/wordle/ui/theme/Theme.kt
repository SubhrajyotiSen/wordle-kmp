package me.subhrajyoti.wordle.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NeonTokyoColorScheme = darkColorScheme(
    primary = NeonTokyoColors.Primary,
    onPrimary = NeonTokyoColors.OnPrimary,
    primaryContainer = NeonTokyoColors.PrimaryContainer,
    onPrimaryContainer = NeonTokyoColors.OnPrimaryContainer,
    secondary = NeonTokyoColors.Secondary,
    onSecondary = NeonTokyoColors.OnSecondary,
    secondaryContainer = NeonTokyoColors.SecondaryContainer,
    onSecondaryContainer = NeonTokyoColors.OnSecondaryContainer,
    tertiary = NeonTokyoColors.Tertiary,
    onTertiary = NeonTokyoColors.OnTertiary,
    tertiaryContainer = NeonTokyoColors.TertiaryContainer,
    onTertiaryContainer = NeonTokyoColors.OnTertiaryContainer,
    error = NeonTokyoColors.Error,
    onError = NeonTokyoColors.OnError,
    errorContainer = NeonTokyoColors.ErrorContainer,
    onErrorContainer = NeonTokyoColors.OnErrorContainer,
    background = NeonTokyoColors.Background,
    onBackground = NeonTokyoColors.OnBackground,
    surface = NeonTokyoColors.Surface,
    onSurface = NeonTokyoColors.OnSurface,
    surfaceVariant = NeonTokyoColors.SurfaceVariant,
    onSurfaceVariant = NeonTokyoColors.OnSurfaceVariant,
    outline = NeonTokyoColors.Outline,
    outlineVariant = NeonTokyoColors.OutlineVariant,
    inverseSurface = NeonTokyoColors.InverseSurface,
    inverseOnSurface = NeonTokyoColors.InverseOnSurface,
    inversePrimary = NeonTokyoColors.InversePrimary,
    surfaceTint = NeonTokyoColors.SurfaceTint,
    surfaceBright = NeonTokyoColors.SurfaceBright,
    surfaceDim = NeonTokyoColors.SurfaceDim,
    surfaceContainer = NeonTokyoColors.SurfaceContainer,
    surfaceContainerHigh = NeonTokyoColors.SurfaceContainerHigh,
    surfaceContainerHighest = NeonTokyoColors.SurfaceContainerHighest,
    surfaceContainerLow = NeonTokyoColors.SurfaceContainerLow,
    surfaceContainerLowest = NeonTokyoColors.SurfaceContainerLowest,
    primaryFixed = NeonTokyoColors.PrimaryFixed,
    onPrimaryFixed = NeonTokyoColors.OnPrimaryFixed,
    primaryFixedDim = NeonTokyoColors.PrimaryFixedDim,
    onPrimaryFixedVariant = NeonTokyoColors.OnPrimaryFixedVariant,
    secondaryFixed = NeonTokyoColors.SecondaryFixed,
    onSecondaryFixed = NeonTokyoColors.OnSecondaryFixed,
    secondaryFixedDim = NeonTokyoColors.SecondaryFixedDim,
    onSecondaryFixedVariant = NeonTokyoColors.OnSecondaryFixedVariant,
    tertiaryFixed = NeonTokyoColors.TertiaryFixed,
    onTertiaryFixed = NeonTokyoColors.OnTertiaryFixed,
    tertiaryFixedDim = NeonTokyoColors.TertiaryFixedDim,
    onTertiaryFixedVariant = NeonTokyoColors.OnTertiaryFixedVariant
)

@Composable
fun NeonTokyoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = NeonTokyoColorScheme,
        typography = Typography,
        content = content
    )
}