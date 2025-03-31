package com.dagd.minecraftfuntimer.ui.handlers

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.MutableState
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object WalkingCreeperInteractionHandler {
    fun onCreeperClick(
        scope: CoroutineScope,
        originalPosition: Float,
        isWalking: MutableState<Boolean>,
        walkingLeftAnimation: Animatable<Float, AnimationVector1D>,
        soundPlayer: SoundPlayer
    ) {
        if (isWalking.value) return

        isWalking.value = true
        soundPlayer.playCreeperSound()

        scope.launch {

            walkingLeftAnimation.animateTo(
                targetValue = 150f,
                animationSpec = tween(1000)
            )

            delay(500)

            walkingLeftAnimation.animateTo(
                targetValue = originalPosition,
                animationSpec = tween(0)
            )
            isWalking.value = false
        }
    }
}