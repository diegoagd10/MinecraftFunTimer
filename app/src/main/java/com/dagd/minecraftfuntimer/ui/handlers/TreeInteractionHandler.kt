package com.dagd.minecraftfuntimer.ui.handlers

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.MutableState
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Handles interactions with the tree element in the Minecraft scene
 */
object TreeInteractionHandler {

    /**
     * Handles the tree click event by animating the yellow butterfly and playing wing sound
     */
    fun onTreeClick(
        scope: CoroutineScope,
        butterflyPaddingBottom: Animatable<Float, AnimationVector1D>,
        isAnimating: MutableState<Boolean>,
        soundPlayer: SoundPlayer? = null
    ) {
        // Only proceed if the butterfly is not already animating
        if (isAnimating.value) return

        isAnimating.value = true

        scope.launch {
            try {
                // Play the wing sound when the animation starts
                soundPlayer?.playWingSound()

                butterflyPaddingBottom.animateTo(
                    targetValue = 440f,
                    animationSpec = tween(500)
                )

                kotlinx.coroutines.delay(500)  // 0.5 second pause

                butterflyPaddingBottom.animateTo(
                    targetValue = 480f,
                    animationSpec = tween(500)
                )

                // Return to original position
                kotlinx.coroutines.delay(200)
                butterflyPaddingBottom.animateTo(
                    targetValue = 330f,
                    animationSpec = tween(300)
                )
            } finally {
                // Always reset the animation state, even if the animation is cancelled
                isAnimating.value = false
            }
        }
    }
} 