package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Minecraft-themed sun component.
 * Displays a simple square sun with rounded corners.
 * Positioning is delegated to the consumer of this component.
 */
@Composable
fun Sun(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    // Simple white box with rounded top-end corner
    Box(
        modifier = modifier
            .size(60.dp)
            .background(
                color = Color(0xFFFDFDFC)
            )
            .testTag("sun")
            .clickable { onClick() }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF07204a)
@Composable
fun SunPreview() {
    MinecraftFunTimerTheme {
        Sun()
    }
} 