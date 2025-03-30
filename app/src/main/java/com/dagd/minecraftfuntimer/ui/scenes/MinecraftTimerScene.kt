package com.dagd.minecraftfuntimer.ui.scenes

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import com.dagd.minecraftfuntimer.ui.handlers.TreeInteractionHandler
import com.dagd.minecraftfuntimer.ui.timer.TimerState

/**
 * Main Minecraft timer scene with night/day toggle capability.
 */
@Composable
fun MinecraftTimerScene(
    timerState: TimerState,
    isNightMode: Boolean,
    onNightModeToggle: () -> Unit,
    onTimerTextClick: () -> Unit,
    onTimerClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val yellowButterflyPaddingBottom = remember { Animatable(330f) }
    val isYellowButterflyAnimating = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val soundPlayer = remember { SoundPlayer(context) }

    // Animation state for creepers
    val firstCreeperPaddingBottom = remember { Animatable(100f) }
    val secondCreeperPaddingBottom = remember { Animatable(80f) }
    val isFirstCreeperAnimating = remember { mutableStateOf(false) }
    val isSecondCreeperAnimating = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        // Render sky background components
        RenderSkyBackground(isNightMode)
        RenderSun(onSunClick = onNightModeToggle)
        RenderClouds()
        RenderStars()

        // Render timer
        RenderCircularTimer(
            progress = timerState.progress,
            timeText = timerState.timerDisplayText,
            isRunning = timerState.isRunning,
            isCompleted = timerState.isCompleted,
            onTimeTextClick = onTimerTextClick,
            onTimerClick = onTimerClick
        )

        // Render ground components
        RenderGround()

        RenderMountainSurfaces(
            scope = scope,
            firstCreeperPaddingBottom = firstCreeperPaddingBottom,
            secondCreeperPaddingBottom = secondCreeperPaddingBottom,
            isFirstCreeperAnimating = isFirstCreeperAnimating,
            isSecondCreeperAnimating = isSecondCreeperAnimating
        )

        RenderTnt()
        RenderTree(
            onTreeClick = {
                TreeInteractionHandler.onTreeClick(
                    scope,
                    yellowButterflyPaddingBottom,
                    isYellowButterflyAnimating,
                    soundPlayer
                )
            }
        )

        RenderButterflies(
            yellowButterflyPaddingBottom = yellowButterflyPaddingBottom.value.toInt()
        )

        RenderCreeper(
            alignment = Alignment.BottomEnd,
            paddingBottom = 50,
            paddingEnd = 10,
            zIndex = 10f
        )
    }
} 