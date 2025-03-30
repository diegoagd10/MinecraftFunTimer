package com.dagd.minecraftfuntimer.ui

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import com.dagd.minecraftfuntimer.ui.components.CircularTimer
import com.dagd.minecraftfuntimer.ui.components.Cloud
import com.dagd.minecraftfuntimer.ui.components.CloudType
import com.dagd.minecraftfuntimer.ui.components.Creeper
import com.dagd.minecraftfuntimer.ui.components.Ground
import com.dagd.minecraftfuntimer.ui.components.SkyBackground
import com.dagd.minecraftfuntimer.ui.components.Star
import com.dagd.minecraftfuntimer.ui.components.StarSize
import com.dagd.minecraftfuntimer.ui.components.Sun
import com.dagd.minecraftfuntimer.ui.components.Surface
import com.dagd.minecraftfuntimer.ui.components.SurfaceType
import com.dagd.minecraftfuntimer.ui.components.Tnt
import com.dagd.minecraftfuntimer.ui.components.Tree
import com.dagd.minecraftfuntimer.ui.timer.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        RenderSkyBackground(isNightMode)
        RenderSun(onSunClick = onNightModeToggle)
        RenderClouds()
        RenderStars()
        RenderCircularTimer(
            progress = timerState.progress,
            timeText = timerState.timerDisplayText,
            isRunning = timerState.isRunning,
            isCompleted = timerState.isCompleted,
            onTimeTextClick = onTimerTextClick,
            onTimerClick = onTimerClick
        )
        Ground(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(5f)
        )
        RenderMountainSurfaces()
        RenderTnt()
        RenderTree()
        RenderCreeper(
            alignment = Alignment.BottomEnd,
            paddingBottom = 50,
            paddingEnd = 10,
            zIndex = 10f
        )
    }
}

@Composable
private fun RenderSkyBackground(isNightMode: Boolean) {
    SkyBackground(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(0f),
        isNightMode = isNightMode
    )
}

@Composable
private fun RenderSun(onSunClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 0.dp, end = 5.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Sun(
            modifier = Modifier.size(60.dp),
            onClick = onSunClick
        )
    }
}

@Composable
private fun RenderCircularTimer(
    progress: Float = 0f,
    timeText: String = "00:00",
    isRunning: Boolean = false,
    isCompleted: Boolean = false,
    onTimeTextClick: () -> Unit = {},
    onTimerClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(4f)
            .padding(top = 225.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        CircularTimer(
            modifier = Modifier.size(240.dp),
            progress = progress,
            timeText = timeText,
            isRunning = isRunning,
            isCompleted = isCompleted,
            onTimeTextClick = onTimeTextClick,
            onTimerClick = onTimerClick
        )
    }
}

@Composable
private fun RenderClouds() {
    // First cloud
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 25.dp, start = 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Cloud(
            modifier = Modifier.size(width = 185.dp, height = 170.dp),
            cloudType = CloudType.TYPE_1
        )
    }

    // Second cloud
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 40.dp, start = 20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Cloud(
            modifier = Modifier.size(width = 90.dp, height = 30.dp),
            cloudType = CloudType.TYPE_2
        )
    }
}

@Composable
private fun RenderStars() {
    // Star 1
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 90.dp, start = 250.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 2
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 100.dp, start = 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.MEDIUM)
    }

    // Star 3
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 50.dp, start = 20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 4
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 200.dp, start = 20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 5
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 380.dp, start = 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 6
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 380.dp, end = 15.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 7
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 17.dp, start = 220.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.SMALL)
    }

    // Star 8
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .padding(top = 200.dp, start = 290.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Star(starSize = StarSize.MEDIUM)
    }
}

@Composable
private fun RenderMountainSurfaces() {
    val scope = rememberCoroutineScope()
    val firstCreeperPaddingBottom = remember { Animatable(100f) }
    val secondCreeperPaddingBottom = remember { Animatable(80f) }
    val context = LocalContext.current
    val soundPlayer = remember { SoundPlayer(context) }
    val isFirstCreeperAnimating = remember { androidx.compose.runtime.mutableStateOf(false) }
    val isSecondCreeperAnimating = remember { androidx.compose.runtime.mutableStateOf(false) }

    // First mountain surface
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp, start = 25.dp)
            .zIndex(10f),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier.size(width = 70.dp, height = 120.dp),
            surfaceType = SurfaceType.TYPE_1,
            onClick = {
                animateCreeperJump(
                    scope, 
                    firstCreeperPaddingBottom, 
                    150f, 
                    100f, 
                    soundPlayer,
                    isFirstCreeperAnimating
                )
            }
        )
    }

    RenderCreeper(
        alignment = Alignment.BottomStart,
        paddingStart = 10,
        paddingBottom = firstCreeperPaddingBottom.value.toInt(),
        zIndex = 9f,
        size = 120
    )

    // Second mountain surface
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp, start = 45.dp)
            .zIndex(11f),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier.size(width = 50.dp, height = 80.dp),
            surfaceType = SurfaceType.TYPE_2,
            onClick = {
                animateCreeperJump(
                    scope, 
                    firstCreeperPaddingBottom, 
                    150f, 
                    100f, 
                    soundPlayer,
                    isFirstCreeperAnimating
                )
            }
        )
    }

    // Third mountain surface
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp, start = 95.dp)
            .zIndex(10f),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier.size(width = 65.dp, height = 100.dp),
            surfaceType = SurfaceType.TYPE_1,
            onClick = {
                animateCreeperJump(
                    scope, 
                    secondCreeperPaddingBottom, 
                    120f, 
                    80f, 
                    soundPlayer,
                    isSecondCreeperAnimating
                )
            }
        )
    }

    RenderCreeper(
        alignment = Alignment.BottomStart,
        paddingStart = 70,
        paddingBottom = secondCreeperPaddingBottom.value.toInt(),
        zIndex = 9f,
        size = 120
    )

    // Fourth mountain surface
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp, start = 120.dp)
            .zIndex(11f),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier.size(width = 50.dp, height = 80.dp),
            surfaceType = SurfaceType.TYPE_2,
            onClick = {
                animateCreeperJump(
                    scope, 
                    secondCreeperPaddingBottom, 
                    120f, 
                    80f, 
                    soundPlayer,
                    isSecondCreeperAnimating
                )
            }
        )
    }
}

@Composable
private fun RenderTnt() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp, start = 73.dp)
            .zIndex(12f),
        contentAlignment = Alignment.BottomStart
    ) {
        val context = LocalContext.current
        Tnt(
            modifier = Modifier.size(60.dp),
            context = context
        )
    }
}

@Composable
private fun RenderTree() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp, end = 10.dp)
            .zIndex(10f),
        contentAlignment = Alignment.BottomEnd
    ) {
        Tree(
            modifier = Modifier.size(width = 200.dp, height = 300.dp)
        )
    }
}

@Composable
private fun RenderCreeper(
    alignment: Alignment,
    paddingBottom: Int = 0,
    paddingStart: Int = 0,
    paddingEnd: Int = 0,
    zIndex: Float = 0f,
    size: Int = 120
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingBottom.dp, start = paddingStart.dp, end = paddingEnd.dp)
            .zIndex(zIndex),
        contentAlignment = alignment
    ) {
        Creeper(
            modifier = Modifier
                .size(size.dp)
                .zIndex(zIndex)
        )
    }
}

private fun animateCreeperJump(
    scope: CoroutineScope,
    creeperPaddingBottom: Animatable<Float, AnimationVector1D>,
    jumpHeight: Float,
    restingHeight: Float,
    soundPlayer: SoundPlayer? = null,
    isAnimating: androidx.compose.runtime.MutableState<Boolean>
) {
    // Only proceed if the creeper is not already animating
    if (isAnimating.value) return
    
    isAnimating.value = true
    
    scope.launch {
        try {
            // Play the sound when the creeper starts to appear
            soundPlayer?.playCreeperSound()
            
            // Animate creeper moving up - slowed down by 30%
            creeperPaddingBottom.animateTo(
                targetValue = jumpHeight,
                animationSpec = tween(650)  // Was 500, increased by 30%
            )
            // Wait a moment - also increased by 30%
            kotlinx.coroutines.delay(650)  // Was 500, increased by 30%
            // Animate creeper moving back down - slowed down by 30%
            creeperPaddingBottom.animateTo(
                targetValue = restingHeight,
                animationSpec = tween(650)  // Was 500, increased by 30%
            )
        } finally {
            // Always reset the animation state, even if the animation is cancelled
            isAnimating.value = false
        }
    }
}