package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Enum to represent the different sizes of stars
 */
enum class StarSize {
    SMALL,  // 2dp x 2dp
    MEDIUM, // 3dp x 3dp
}

/**
 * Minecraft-themed star component.
 * Displays a simple star as a small white dot with shadow effect.
 * Positioning is delegated to the consumer of this component.
 */
@Composable
fun Star(
    modifier: Modifier = Modifier,
    starSize: StarSize = StarSize.SMALL
) {
    // Determine star size
    val size = when (starSize) {
        StarSize.SMALL -> 2.dp
        StarSize.MEDIUM -> 3.dp
    }
    
    // Star represented as a tiny white square with shadow
    Box(
        modifier = modifier
            .size(size)
            .shadow(
                elevation = 5.dp,
                spotColor = Color(0xFFFFFFFB)
            )
            .background(Color(0xFFFDFDFC))
    )
}

@Preview(showBackground = true)
@Composable
fun SmallStarPreview() {
    MinecraftFunTimerTheme {
        Box(modifier = Modifier.size(50.dp)) {
            Star(starSize = StarSize.SMALL)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediumStarPreview() {
    MinecraftFunTimerTheme {
        Box(modifier = Modifier.size(50.dp)) {
            Star(starSize = StarSize.MEDIUM)
        }
    }
} 