package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Explosion animation component with multiple particles
 * similar to the HTML/CSS implementation.
 * The particles start from the center and move outward.
 */
@Composable
fun Explosion(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    onExplosionComplete: () -> Unit = {}
) {
    // Track whether explosion has completed
    val hasCompleted = remember { mutableStateOf(false) }
    val particleCount = 15 // Increased from 13 to 15 for more particles
    
    // Reset state when becoming active
    LaunchedEffect(isActive) {
        if (isActive) {
            hasCompleted.value = false
            // Wait for animation to complete plus a little buffer
            // Increased to 1000ms to match longer animation duration
            delay(1000)
            hasCompleted.value = true
            onExplosionComplete()
        }
    }
    
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center // Ensure all particles are centered
    ) {
        if (isActive && !hasCompleted.value) {
            // Generate the particles in different directions
            // Particle 1 - Yellow/Red (UP_LEFT)
            ExplosionParticle(
                trajectory = ParticleTrajectory.UP_LEFT,
                size = 12f, // Increased from 8f to 12f
                color = Color(0xFFFF5722),
                animationDuration = 850 // Increased from 700 to 850
            )
            
            // Particle 2 - Yellow/Red (UP_RIGHT)
            ExplosionParticle(
                trajectory = ParticleTrajectory.UP_RIGHT,
                size = 10f, // Increased from 6f to 10f
                color = Color(0xFFFF5722),
                delayStart = 50,
                animationDuration = 800 // Increased from 650 to 800
            )
            
            // Particle 3 - Yellow/Orange (LEFT)
            ExplosionParticle(
                trajectory = ParticleTrajectory.LEFT,
                size = 11f, // Increased from 7f to 11f
                color = Color(0xFFFF9800),
                delayStart = 25,
                animationDuration = 850 // Increased from 700 to 850
            )
            
            // Particle 4 - Yellow/Orange (RIGHT)
            ExplosionParticle(
                trajectory = ParticleTrajectory.RIGHT,
                size = 9f, // Increased from 5f to 9f
                color = Color(0xFFFF9800),
                delayStart = 75,
                animationDuration = 800 // Increased from 600 to 800
            )
            
            // Particle 5 - Bright Yellow (UP)
            ExplosionParticle(
                trajectory = ParticleTrajectory.UP,
                size = 12f, // Increased from 8f to 12f
                color = Color(0xFFFFEB3B),
                delayStart = 20,
                animationDuration = 900 // Increased from 750 to 900
            )
            
            // Particle 6 - Bright Yellow (DOWN)
            ExplosionParticle(
                trajectory = ParticleTrajectory.DOWN,
                size = 11f, // Increased from 7f to 11f
                color = Color(0xFFFFEB3B),
                delayStart = 90,
                animationDuration = 800 // Increased from 650 to 800
            )
            
            // Particle 7 - Red (DOWN_LEFT)
            ExplosionParticle(
                trajectory = ParticleTrajectory.DOWN_LEFT,
                size = 10f, // Increased from 6f to 10f
                color = Color(0xFFFF5722),
                delayStart = 10,
                animationDuration = 850 // Increased from 700 to 850
            )
            
            // Particle 8 - Red (DOWN_RIGHT)
            ExplosionParticle(
                trajectory = ParticleTrajectory.DOWN_RIGHT,
                size = 9f, // Increased from 5f to 9f
                color = Color(0xFFFF5722),
                delayStart = 40,
                animationDuration = 800 // Increased from 600 to 800
            )
            
            // Additional particles with various colors
            // Particle 9
            ExplosionParticle(
                trajectory = ParticleTrajectory.UP_LEFT,
                size = 8f, // Increased from 4f to 8f
                color = Color(0xFFFFEB3B),
                delayStart = 60,
                animationDuration = 750 // Increased from 550 to 750
            )
            
            // Particle 10
            ExplosionParticle(
                trajectory = ParticleTrajectory.UP_RIGHT,
                size = 10f, // Increased from 6f to 10f
                color = Color(0xFFFF9800),
                delayStart = 30,
                animationDuration = 800 // Increased from 650 to 800
            )
            
            // Particle 11
            ExplosionParticle(
                trajectory = ParticleTrajectory.DOWN_LEFT,
                size = 9f, // Increased from 5f to 9f
                color = Color(0xFFFFEB3B),
                delayStart = 80,
                animationDuration = 750 // Increased from 600 to 750
            )
            
            // Particle 12
            ExplosionParticle(
                trajectory = ParticleTrajectory.DOWN_RIGHT,
                size = 11f, // Increased from 7f to 11f
                color = Color(0xFFFF5722),
                delayStart = 35,
                animationDuration = 850 // Increased from 700 to 850
            )
            
            // Particle 13
            ExplosionParticle(
                trajectory = ParticleTrajectory.LEFT,
                size = 9f, // Increased from 5f to 9f
                color = Color(0xFFFFEB3B),
                delayStart = 70,
                animationDuration = 800 // Increased from 650 to 800
            )
            
            // New particles for an even bigger explosion
            // Particle 14
            ExplosionParticle(
                trajectory = ParticleTrajectory.RIGHT,
                size = 10f,
                color = Color(0xFFFF5722),
                delayStart = 15,
                animationDuration = 900
            )
            
            // Particle 15
            ExplosionParticle(
                trajectory = ParticleTrajectory.UP,
                size = 11f,
                color = Color(0xFFFF9800),
                delayStart = 45,
                animationDuration = 850
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExplosionPreview() {
    Box(
        modifier = Modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Explosion(
            modifier = Modifier.size(100.dp),
            isActive = true
        )
    }
} 