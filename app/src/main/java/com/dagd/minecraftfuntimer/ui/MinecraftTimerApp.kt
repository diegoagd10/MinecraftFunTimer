package com.dagd.minecraftfuntimer.ui

import androidx.compose.foundation.layout.Box
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
        RenderSkyBackground()
        Ground(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(5f)
        )
        RenderMountainSurfaces()
        RenderTnt()
        RenderTree()
        RenderCreeper()
    }
}

@Composable
private fun RenderSkyBackground() {
    SkyBackground(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(0f),
        initialIsNightMode = true
    )
}

@Composable
private fun RenderMountainSurfaces() {
    // First mountain surface
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

    // Second mountain surface
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

    // Third mountain surface
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

    // Fourth mountain surface
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
}

@Composable
private fun RenderTnt() {
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
}

@Composable
private fun RenderTree() {
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
}

@Composable
private fun RenderCreeper() {
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

@Preview(showBackground = true)
@Composable
fun MinecraftTimerAppPreview() {
    MinecraftFunTimerTheme {
        MinecraftTimerApp()
    }
} 