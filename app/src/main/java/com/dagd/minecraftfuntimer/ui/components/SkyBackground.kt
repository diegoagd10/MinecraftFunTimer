package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Sky background component that displays day or night background.
 * No longer changes on click - this is now handled by the Sun component.
 */
@Composable
fun SkyBackground(
    modifier: Modifier = Modifier,
    initialIsNightMode: Boolean = true,
    isNightMode: Boolean = initialIsNightMode
) {
    // Background colors
    val nightSkyColor = Color(0xFF07204a) // Dark blue for night sky
    val daySkyColor = Color(0xFF8EC5FC) // Light blue for day sky
    
    // Current background color based on mode
    val backgroundColor = if (isNightMode) nightSkyColor else daySkyColor
    
    // Sky background without click behavior
    Box(
        modifier = modifier
            .background(backgroundColor)
    )
}

@Preview(showBackground = true)
@Composable
fun SkyBackgroundPreview() {
    MinecraftFunTimerTheme {
        SkyBackground()
    }
} 