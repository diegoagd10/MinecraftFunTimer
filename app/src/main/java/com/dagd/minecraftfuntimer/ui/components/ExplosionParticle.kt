package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * Defines a particle trajectory for the explosion
 */
enum class ParticleTrajectory {
    UP_LEFT,
    UP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT,
    LEFT,
    RIGHT,
    UP,
    DOWN
}

/**
 * Explosion particle component that animates from a center position outward
 * with a fading effect, similar to the HTML/CSS implementation.
 */
@Composable
fun ExplosionParticle(
    modifier: Modifier = Modifier,
    trajectory: ParticleTrajectory,
    size: Float = 10f,
    color: Color = Color(0xFFFF5722),
    delayStart: Int = 0, // Delay in milliseconds before starting animation
    animationDuration: Int = 500, // Animation duration in milliseconds
    onAnimationFinished: () -> Unit = {}
) {
    // Position offsets
    val xOffset = remember { Animatable(0f) }
    val yOffset = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }
    
    // Randomize movement slightly for more natural look
    // Using a wider range of randomness for more varied particle movement
    val randomFactor = remember { Random.nextFloat() * 0.4f + 0.8f } // Increased from 0.6f to 0.8f for larger distance
    val randomAngleOffset = remember { (Random.nextFloat() - 0.5f) * 15f } // +/- 15 degrees variation
    
    // Calculate target position based on trajectory with angle variation
    val (targetX, targetY) = remember {
        when (trajectory) {
            ParticleTrajectory.UP_LEFT -> {
                val distance = 80f * randomFactor // Increased from 50f to 80f
                val baseAngle = 135f + randomAngleOffset
                val radians = Math.toRadians(baseAngle.toDouble())
                Pair(
                    (distance * Math.cos(radians)).toFloat(),
                    (distance * Math.sin(radians)).toFloat()
                )
            }
            ParticleTrajectory.UP_RIGHT -> {
                val distance = 80f * randomFactor // Increased from 50f to 80f
                val baseAngle = 45f + randomAngleOffset
                val radians = Math.toRadians(baseAngle.toDouble())
                Pair(
                    (distance * Math.cos(radians)).toFloat(),
                    (distance * Math.sin(radians)).toFloat()
                )
            }
            ParticleTrajectory.DOWN_LEFT -> {
                val distance = 80f * randomFactor // Increased from 50f to 80f
                val baseAngle = 225f + randomAngleOffset
                val radians = Math.toRadians(baseAngle.toDouble())
                Pair(
                    (distance * Math.cos(radians)).toFloat(),
                    (distance * Math.sin(radians)).toFloat()
                )
            }
            ParticleTrajectory.DOWN_RIGHT -> {
                val distance = 80f * randomFactor // Increased from 50f to 80f
                val baseAngle = 315f + randomAngleOffset
                val radians = Math.toRadians(baseAngle.toDouble())
                Pair(
                    (distance * Math.cos(radians)).toFloat(),
                    (distance * Math.sin(radians)).toFloat()
                )
            }
            ParticleTrajectory.LEFT -> Pair(-90f * randomFactor, randomAngleOffset * 2f) // Increased from 60f to 90f
            ParticleTrajectory.RIGHT -> Pair(90f * randomFactor, randomAngleOffset * 2f) // Increased from 60f to 90f
            ParticleTrajectory.UP -> Pair(randomAngleOffset * 2f, -90f * randomFactor) // Increased from 60f to 90f
            ParticleTrajectory.DOWN -> Pair(randomAngleOffset * 2f, 90f * randomFactor) // Increased from 60f to 90f
        }
    }
    
    LaunchedEffect(key1 = true) {
        delay(delayStart.toLong())
        
        launch {
            xOffset.animateTo(
                targetValue = targetX,
                animationSpec = tween(
                    durationMillis = animationDuration,
                    easing = FastOutLinearInEasing
                )
            )
        }
        
        launch {
            yOffset.animateTo(
                targetValue = targetY,
                animationSpec = tween(
                    durationMillis = animationDuration,
                    easing = FastOutLinearInEasing
                )
            )
        }
        
        launch {
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = animationDuration,
                    easing = LinearOutSlowInEasing
                )
            )
            
            // Notify when animation is finished
            onAnimationFinished()
        }
    }
    
    Box(
        modifier = modifier
            .offset { IntOffset(xOffset.value.roundToInt(), yOffset.value.roundToInt()) }
            .alpha(alpha.value)
    ) {
        Canvas(modifier = Modifier.size(size.dp)) {
            drawCircle(
                color = color,
                radius = size / 2
            )
        }
    }
} 