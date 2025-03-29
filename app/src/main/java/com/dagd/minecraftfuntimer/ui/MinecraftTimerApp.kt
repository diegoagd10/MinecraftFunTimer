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
import com.dagd.minecraftfuntimer.ui.components.Cloud
import com.dagd.minecraftfuntimer.ui.components.CloudType
import com.dagd.minecraftfuntimer.ui.components.Creeper
import com.dagd.minecraftfuntimer.ui.components.Ground
import com.dagd.minecraftfuntimer.ui.components.SkyBackground
import com.dagd.minecraftfuntimer.ui.components.Star
import com.dagd.minecraftfuntimer.ui.components.StarSize
import com.dagd.minecraftfuntimer.ui.components.Sun
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
        RenderSun()
        RenderClouds()
        RenderStars()
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
private fun RenderSun() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 0.dp, end = 5.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Sun(
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
private fun RenderClouds() {
    // First cloud
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 25.dp, start = 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Cloud(
            modifier = Modifier.size(width = 155.dp, height = 100.dp),
            cloudType = CloudType.TYPE_1
        )
    }

    // Second cloud
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 40.dp, start = 20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Cloud(
            modifier = Modifier.size(width = 60.dp, height = 30.dp),
            cloudType = CloudType.TYPE_2
        )
    }
}

@Composable
private fun RenderStars() {
    // Star 1
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 90.dp, start = 250.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 2
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 100.dp, start = 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.MEDIUM)
    }

    // Star 3
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 50.dp, start = 20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 4
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 200.dp, start = 20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 5
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 380.dp, start = 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 6
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 380.dp, end = 15.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 7
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 17.dp, start = 220.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 8
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 200.dp, start = 290.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.MEDIUM)
    }
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