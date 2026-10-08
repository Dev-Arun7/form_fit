package com.example.formfit.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ElectricVolt,
    onPrimary = CanvasBackground,
    primaryContainer = ElectricVoltDim,
    onPrimaryContainer = ElectricVolt,
    secondary = LuminousLime,
    onSecondary = CanvasBackground,
    tertiary = KineticCyan,
    onTertiary = CanvasBackground,
    background = CanvasBackground,
    onBackground = TextPrimary,
    surface = SurfaceElevated,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceSubtle,
    onSurfaceVariant = TextSecondary,
    outline = BorderHairline,
    outlineVariant = BorderActive
)

@Composable
fun FormFitTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
