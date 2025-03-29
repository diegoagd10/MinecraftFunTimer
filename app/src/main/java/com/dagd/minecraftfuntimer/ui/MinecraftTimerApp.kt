package com.dagd.minecraftfuntimer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.compose.ui.tooling.preview.Preview
import com.dagd.minecraftfuntimer.ui.components.Ground
import com.dagd.minecraftfuntimer.ui.components.SkyBackground
import com.dagd.minecraftfuntimer.ui.components.Tree
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Main composable for the Minecraft themed timer application.
 * With modular components for sky background and ground.
 */
@Composable
fun MinecraftTimerApp() {
    // Main container - no background color needed
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Sky background component (lowest z-index layer)
        SkyBackground(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f),
            initialIsNightMode = true
        )
        
        // Ground component at the bottom - ensure it's flush with the bottom edge
        Ground(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(5f)
        )
        
        // Tree component (highest layer, should appear in front of ground)
        Tree(
            modifier = Modifier
                .align(Alignment.BottomCenter)
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