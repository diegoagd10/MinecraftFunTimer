package com.dagd.minecraftfuntimer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.ui.tooling.preview.Preview
import com.dagd.minecraftfuntimer.ui.components.Ground
import com.dagd.minecraftfuntimer.ui.components.SkyBackground
import com.dagd.minecraftfuntimer.ui.components.Tree
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Main composable for the Minecraft themed timer application.
 */
@Composable
fun MinecraftTimerApp() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background - Sky
        SkyBackground(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f),
            initialIsNightMode = true
        )
        
        // Foreground elements
        
        // Ground at the bottom
        Ground(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(5f)
        )

        Tree(
            modifier = Modifier
                .size(300.dp)
                .offset(x = 140.dp, y = 500.dp)
                .zIndex(10f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MinecraftTimerAppPreview() {
    MinecraftFunTimerTheme {
        MinecraftTimerApp()
    }
} 