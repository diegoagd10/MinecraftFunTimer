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
 * Enum to represent the different types of clouds
 */
enum class CloudType {
    TYPE_1, // Corresponds to nube.png (cloud1)
    TYPE_2  // Corresponds to nube2.png (cloud2)
}

/**
 * Minecraft-themed cloud component.
 * Displays a cloud image with the specified type.
 * Positioning is delegated to the consumer of this component.
 */
@Composable
fun Cloud(
    modifier: Modifier = Modifier,
    cloudType: CloudType = CloudType.TYPE_1
) {
    val imageRes = when (cloudType) {
        CloudType.TYPE_1 -> R.drawable.nube
        CloudType.TYPE_2 -> R.drawable.nube2
    }

    Box(modifier = modifier) {
        // Cloud image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Minecraft Cloud",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Cloud1Preview() {
    MinecraftFunTimerTheme {
        Cloud(
            modifier = Modifier.size(100.dp),
            cloudType = CloudType.TYPE_1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Cloud2Preview() {
    MinecraftFunTimerTheme {
        Cloud(
            modifier = Modifier.size(100.dp),
            cloudType = CloudType.TYPE_2
        )
    }
} 