package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Minecraft-themed creeper component.
 * Displays a creeper with body and head.
 * Positioning and sizing is delegated to the consumer of this component.
 */
@Composable
fun Creeper(
    modifier: Modifier = Modifier
) {
    Box (modifier = modifier) {
        // Creeper body
        Image(
            painter = painterResource(id = R.drawable.cuerpo_shon),
            contentDescription = "Creeper Body",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(width = 55.dp, height = 87.dp),
            contentScale = ContentScale.FillBounds
        )
        
        // Creeper head (positioned slightly above the body)
        Image(
            painter = painterResource(id = R.drawable.cabeza_shon),
            contentDescription = "Creeper Head",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(width = 43.dp, height = 34.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreeperPreview() {
    MinecraftFunTimerTheme {
        Creeper(
            modifier = Modifier.size(width = 100.dp, height = 120.dp)
        )
    }
} 