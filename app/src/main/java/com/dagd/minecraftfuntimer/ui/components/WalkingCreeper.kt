package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Minecraft-themed Walking Creeper component.
 * Displays a creeper with head, body and feet for a walking animation.
 * No animation is implemented in this static version.
 */
@Composable
fun WalkingCreeper(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {

        // Creeper head
        Image(
            painter = painterResource(id = R.drawable.cabeza_shon),
            contentDescription = "Creeper Head",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(width = 43.dp, height = 34.dp)
                .zIndex(2f),
            contentScale = ContentScale.FillBounds
        )

        // Creeper body
        Image(
            painter = painterResource(id = R.drawable.cuerpo_shon1),
            contentDescription = "Creeper Body",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .size(width = 40.dp, height = 77.dp)
                .zIndex(2f),
            contentScale = ContentScale.FillBounds
        )

        // Creeper feet
        Image(
            painter = painterResource(id = R.drawable.patita1),
            contentDescription = "Creeper Left Foot",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 27.dp)
                .size(width = 28.dp, height = 37.dp)
                .zIndex(3f),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            painter = painterResource(id = R.drawable.patita2),
            contentDescription = "Creeper Middle Foot",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 37.dp)
                .size(width = 28.dp, height = 37.dp)
                .zIndex(3f),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            painter = painterResource(id = R.drawable.patita3),
            contentDescription = "Creeper Right Foot",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 3.dp)
                .size(width = 28.dp, height = 37.dp)
                .zIndex(1f),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WalkingCreeperPreview() {
    MinecraftFunTimerTheme {
        WalkingCreeper(
            modifier = Modifier.size(120.dp)
        )
    }
} 