package com.dagd.minecraftfuntimer.ui.scenes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.ui.components.Cloud
import com.dagd.minecraftfuntimer.ui.components.CloudType
import com.dagd.minecraftfuntimer.ui.components.SkyBackground
import com.dagd.minecraftfuntimer.ui.components.Star
import com.dagd.minecraftfuntimer.ui.components.StarSize
import com.dagd.minecraftfuntimer.ui.components.Sun

@Composable
fun RenderSkyBackground(isNightMode: Boolean) {
    SkyBackground(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(0f),
        isNightMode = isNightMode
    )
}

@Composable
fun RenderSun(onSunClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 0.dp, end = 5.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Sun(
            modifier = Modifier.size(60.dp),
            onClick = onSunClick
        )
    }
}

@Composable
fun RenderClouds() {
    // First cloud
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 25.dp, start = 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Cloud(
            modifier = Modifier.size(width = 185.dp, height = 170.dp),
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
            modifier = Modifier.size(width = 90.dp, height = 30.dp),
            cloudType = CloudType.TYPE_2
        )
    }
}

@Composable
fun RenderStars() {
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