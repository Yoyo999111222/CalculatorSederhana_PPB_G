package com.example.calculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light color scheme
private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFF5F5F5),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

// Define typography (material3 typography)
val Typography = Typography()

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = false, // Choose dark theme based on system or a manual toggle
    content: @Composable () -> Unit
) {
    val colors = LightColorPalette // Use LightColorPalette or DarkColorPalette based on your theme setting

    MaterialTheme(
        colorScheme = colors,
        typography = Typography, // Use Typography from Material3
        content = content
    )
}
