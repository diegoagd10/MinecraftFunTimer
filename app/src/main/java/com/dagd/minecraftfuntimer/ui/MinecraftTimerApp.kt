package com.dagd.minecraftfuntimer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.ui.components.Creeper
import com.dagd.minecraftfuntimer.ui.components.Ground
import com.dagd.minecraftfuntimer.ui.components.SkyBackground
import com.dagd.minecraftfuntimer.ui.components.Surface
import com.dagd.minecraftfuntimer.ui.components.SurfaceType
import com.dagd.minecraftfuntimer.ui.components.Tnt
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
        
        // Single mountain surface block
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp, start = 25.dp)
                .zIndex(10f),
            contentAlignment = Alignment.BottomStart
        ) {
            Surface(
                modifier = Modifier.size(width = 70.dp, height = 120.dp),
                surfaceType = SurfaceType.TYPE_1
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 75.dp, start = 45.dp)
                .zIndex(11f),
            contentAlignment = Alignment.BottomStart
        ) {
            Surface(
                modifier = Modifier.size(width = 50.dp, height = 80.dp),
                surfaceType = SurfaceType.TYPE_2
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp, start = 95.dp)
                .zIndex(10f),
            contentAlignment = Alignment.BottomStart
        ) {
            Surface(
                modifier = Modifier.size(width = 65.dp, height = 100.dp),
                surfaceType = SurfaceType.TYPE_1
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 75.dp, start = 120.dp)
                .zIndex(11f),
            contentAlignment = Alignment.BottomStart
        ) {
            Surface(
                modifier = Modifier.size(width = 50.dp, height = 80.dp),
                surfaceType = SurfaceType.TYPE_2
            )
        }

        // TNT block - positioned at bottom left similar to HTML implementation
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp, start = 73.dp)
                .zIndex(12f),
            contentAlignment = Alignment.BottomStart
        ) {
            Tnt(
                modifier = Modifier.size(60.dp)
            )
        }

        // Tree
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp, end = 10.dp)
                .zIndex(10f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Tree(
                modifier = Modifier.size(width = 200.dp, height = 300.dp)
            )
        }

        // Creeper
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 35.dp, end = 5.dp)
                .zIndex(10f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Creeper(
                modifier = Modifier.size(120.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MinecraftTimerAppPreview() {
    MinecraftFunTimerTheme {
        MinecraftTimerApp()
    }
} 