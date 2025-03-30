package com.dagd.minecraftfuntimer.ui.handlers

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.MutableState
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Handles interactions with the tree element in the Minecraft scene
 */
object TreeInteractionHandler {

    /**
     * Handles the tree click event by coordinating the butterfly animations and playing wing sound
     */
    fun onTreeClick(
        scope: CoroutineScope,
        yellowButterflyPaddingBottom: Animatable<Float, AnimationVector1D>,
        purpleButterflyPaddingBottom: Animatable<Float, AnimationVector1D>,
        purpleButterflyPaddingEnd: Animatable<Float, AnimationVector1D>,
        isAnimating: MutableState<Boolean>,
        soundPlayer: SoundPlayer? = null
    ) {
        // Only proceed if an animation is not already running
        if (isAnimating.value) return

        isAnimating.value = true
        
        // Play the wing sound when the animation starts
        soundPlayer?.playWingSound()
        
        // Start both butterfly animations
        scope.launch {
            try {
                // Launch both animations in parallel
                coroutineScope {
                    launch {
                        animateYellowButterfly(yellowButterflyPaddingBottom)
                    }
                    
                    launch {
                        animatePurpleButterfly(purpleButterflyPaddingBottom, purpleButterflyPaddingEnd)
                    }
                }
                
                // No need for additional delay since coroutineScope waits for all child coroutines to complete
            } finally {
                // Reset the animation state when all animations are done
                isAnimating.value = false
            }
        }
    }
    
    /**
     * Handles the animation of the yellow butterfly
     */
    private suspend fun animateYellowButterfly(
        paddingBottom: Animatable<Float, AnimationVector1D>
    ) {
        paddingBottom.animateTo(
            targetValue = 440f,
            animationSpec = tween(500)
        )

        delay(500)  // 0.5 second pause

        paddingBottom.animateTo(
            targetValue = 480f,
            animationSpec = tween(500)
        )

        // Return to original position
        delay(200)
        paddingBottom.animateTo(
            targetValue = 330f,
            animationSpec = tween(300)
        )
    }
    
    /**
     * Handles the animation of the purple butterfly
     */
    private suspend fun animatePurpleButterfly(
        paddingBottom: Animatable<Float, AnimationVector1D>,
        paddingEnd: Animatable<Float, AnimationVector1D>
    ) {
        // First animate both position properties to initial target position
        paddingBottom.animateTo(
            targetValue = 430f,
            animationSpec = tween(500)
        )
        
        paddingEnd.animateTo(
            targetValue = 150f,
            animationSpec = tween(500)
        )

        // Return to original position
        delay(200)
        paddingBottom.animateTo(
            targetValue = 330f,
            animationSpec = tween(300)
        )
        
        paddingEnd.animateTo(
            targetValue = 140f,
            animationSpec = tween(300)
        )
    }
} 