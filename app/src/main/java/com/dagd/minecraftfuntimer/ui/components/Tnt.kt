package com.dagd.minecraftfuntimer.ui.components

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme
import kotlinx.coroutines.delay

/**
 * Enum representing the different states of TNT detonation
 */
enum class TntDetonationState {
    IDLE,
    DETONATING,
    EXPLODED
}

/**
 * Manages the TNT state for detonation and explosion
 */
class TntState {
    val detonationState = mutableStateOf(TntDetonationState.IDLE)
    val isExploding = mutableStateOf(false)
    val isVisible = mutableStateOf(true)
    val flashAlpha = mutableStateOf(0f)

    fun detonate() {
        detonationState.value = TntDetonationState.DETONATING
        isVisible.value = true
    }

    fun explode() {
        detonationState.value = TntDetonationState.EXPLODED
        isExploding.value = true
        isVisible.value = false
    }

    fun reset() {
        detonationState.value = TntDetonationState.IDLE
        isExploding.value = false
        isVisible.value = true
        flashAlpha.value = 0f
    }
}

/**
 * Minecraft-themed TNT component with animation.
 * When clicked, the TNT will play a ticking sound, flash white,
 * and then explode with an explosion animation and sound.
 * This implementation closely mimics the HTML/CSS version with particle explosions.
 */
@Composable
fun Tnt(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    val tntState = remember { TntState() }
    val soundPlayer = remember { SoundPlayer(context) }
    
    // Manage detonation and explosion animations
    val infiniteTransition = rememberInfiniteTransition(label = "tnt_flashing")
    val flashingAlpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(250), // Faster flashing (250ms instead of 500ms)
            repeatMode = RepeatMode.Reverse
        ),
        label = "tnt_flash"
    )
    
    // Reset the TNT after explosion
    LaunchedEffect(tntState.detonationState.value) {
        if (tntState.detonationState.value == TntDetonationState.DETONATING) {
            // Play ticking sound
            soundPlayer.playTickingSound()
            
            // Wait for a bit then trigger explosion
            delay(2500)
            tntState.explode()
            
            // Play explosion sound
            soundPlayer.playExplosionSound()
            
            // Wait for explosion animation to complete
            delay(1200)
            
            // Reset the TNT state
            tntState.reset()
        }
    }
    
    // Cleanup resources when component is disposed
    DisposableEffect(Unit) {
        onDispose {
            soundPlayer.releaseAll()
        }
    }
    
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center // Center everything in the container
    ) {
        // TNT block
        AnimatedVisibility(
            visible = tntState.isVisible.value,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        enabled = tntState.detonationState.value == TntDetonationState.IDLE,
                        onClick = { tntState.detonate() }
                    )
            ) {
                // Main TNT image
                Image(
                    painter = painterResource(id = R.drawable.tnt),
                    contentDescription = "TNT Block",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                
                // White cube overlay for flashing effect (similar to the JavaScript version)
                if (tntState.detonationState.value == TntDetonationState.DETONATING) {
                    // Using the dedicated white cube image
                    Image(
                        painter = painterResource(id = R.drawable.cubo_blanco),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(flashingAlpha),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
        
        // Explosion effect using particles
        if (tntState.isExploding.value) {
            val tntSize = modifier.toString().let {
                // Extract size from modifier if available, or use default
                val sizeRegex = "size: ([0-9]+\\.?[0-9]*)dp".toRegex()
                val match = sizeRegex.find(it)
                match?.groupValues?.get(1)?.toFloatOrNull() ?: 60f
            }
            
            Explosion(
                modifier = Modifier.size((tntSize * 2.5f).dp),
                isActive = true,
                onExplosionComplete = { 
                    if (tntState.detonationState.value == TntDetonationState.EXPLODED) {
                        tntState.reset()
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TntPreview() {
    MinecraftFunTimerTheme {
        Tnt(
            modifier = Modifier.size(100.dp)
        )
    }
} 