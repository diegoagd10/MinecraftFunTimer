package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Minecraft-themed grass ground component that scales with screen size
 */
@Composable
fun Ground(
    modifier: Modifier = Modifier
) {
    // Get screen height to calculate proportional grass height
    val screenHeight = LocalConfiguration.current.screenHeightDp
    
    // For a standard phone (around 640dp height), grass is 93dp
    // Calculate a proportional height for other devices
    // But with a minimum height to ensure visibility
    val grassHeightPercentage = 0.145f // 93dp / 640dp ≈ 14.5% of screen height
    val grassHeight = (screenHeight * grassHeightPercentage).dp.coerceAtLeast(70.dp)
    
    // Grass ground at the bottom with responsive height
    Image(
        painter = painterResource(id = R.drawable.pasto),
        contentDescription = "Grass ground",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .zIndex(5f) // Explicit z-index that's lower than the tree's z-index
            .fillMaxWidth()
            .height(grassHeight)
    )
}

@Preview(showBackground = true)
@Composable
fun GroundPreview() {
    MinecraftFunTimerTheme {
        Ground()
    }
} 