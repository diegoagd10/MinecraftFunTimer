package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Minecraft surface block component that represents mountains/terrain.
 * Two types of surface blocks are available: Type 1 and Type 2.
 * Positioning and sizing is delegated to the consumer of this component.
 */
@Composable
fun Surface(
    modifier: Modifier = Modifier,
    surfaceType: SurfaceType = SurfaceType.TYPE_1
) {
    val imageRes = when (surfaceType) {
        SurfaceType.TYPE_1 -> R.drawable.superficie1
        SurfaceType.TYPE_2 -> R.drawable.superficie2
    }
    
    Box(modifier = modifier) {
        // Surface/mountain block image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Minecraft Surface Block",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

/**
 * Enum to represent the different types of surface blocks
 */
enum class SurfaceType {
    TYPE_1, // Corresponds to superficie1.png
    TYPE_2  // Corresponds to superficie2.png
}

@Preview(showBackground = true)
@Composable
fun SurfacePreview() {
    MinecraftFunTimerTheme {
        Box {
            Surface(
                modifier = Modifier.size(100.dp),
                surfaceType = SurfaceType.TYPE_1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Surface2Preview() {
    MinecraftFunTimerTheme {
        Box {
            Surface(
                modifier = Modifier.size(100.dp),
                surfaceType = SurfaceType.TYPE_2
            )
        }
    }
} 