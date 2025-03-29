package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Sky background component that can toggle between night and day modes.
 * Background color changes on click.
 */
@Composable
fun SkyBackground(
    modifier: Modifier = Modifier,
    initialIsNightMode: Boolean = true,
    onModeChanged: (Boolean) -> Unit = {}
) {
    // State for night/day mode
    var isNightMode by remember { mutableStateOf(initialIsNightMode) }
    
    // Background colors
    val nightSkyColor = Color(0xFF07204a) // Dark blue for night sky
    val daySkyColor = Color(0xFF8EC5FC) // Light blue for day sky
    
    // Current background color based on mode
    val backgroundColor = if (isNightMode) nightSkyColor else daySkyColor
    
    // Sky background that toggles on click
    Box(
        modifier = modifier
            .background(backgroundColor)
            .clickable {
                isNightMode = !isNightMode
                onModeChanged(isNightMode)
            }
    )
}

@Preview(showBackground = true)
@Composable
fun SkyBackgroundPreview() {
    MinecraftFunTimerTheme {
        SkyBackground()
    }
} 