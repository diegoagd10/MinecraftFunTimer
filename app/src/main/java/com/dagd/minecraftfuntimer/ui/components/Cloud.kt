package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme
import kotlinx.coroutines.launch

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
 * The cloud can be clicked to make it disappear and reappear.
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

    val context = LocalContext.current
    val soundPlayer = remember { SoundPlayer(context) }
    val scope = rememberCoroutineScope()
    
    // Cloud opacity animation
    val cloudOpacity = remember { Animatable(1f) }
    val isDisappearing = remember { mutableStateOf(false) }
    
    // Handle cloud fading and reappearing
    LaunchedEffect(isDisappearing.value) {
        if (isDisappearing.value) {
            // Animate to transparent (disappear)
            cloudOpacity.animateTo(
                targetValue = 0f,
                animationSpec = tween(2000)
            )
            
            // Wait some time before reappearing
            kotlinx.coroutines.delay(1000)
            
            // Animate back to visible
            cloudOpacity.animateTo(
                targetValue = 1f,
                animationSpec = tween(1000)
            )
            
            // Reset state
            isDisappearing.value = false
        }
    }

    Box(
        modifier = modifier
            .alpha(cloudOpacity.value)
            .clickable {
                if (!isDisappearing.value) {
                    // Play cloud sound
                    scope.launch {
                        // Play cloud dissolve sound
                        soundPlayer.playCloudSound()
                    }
                    
                    // Start the disappearing animation
                    isDisappearing.value = true
                }
            }
    ) {
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