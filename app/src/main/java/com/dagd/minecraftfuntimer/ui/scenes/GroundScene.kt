package com.dagd.minecraftfuntimer.ui.scenes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import com.dagd.minecraftfuntimer.ui.components.Creeper
import com.dagd.minecraftfuntimer.ui.components.Ground
import com.dagd.minecraftfuntimer.ui.components.Surface
import com.dagd.minecraftfuntimer.ui.components.SurfaceType
import com.dagd.minecraftfuntimer.ui.components.Tnt
import com.dagd.minecraftfuntimer.ui.components.Tree
import com.dagd.minecraftfuntimer.ui.components.WalkingCreeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RenderGround() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Ground(
            modifier = Modifier.zIndex(5f)
        )
    }
}

@Composable
fun RenderMountainSurfaces(
    scope: CoroutineScope,
    firstCreeperPaddingBottom: Animatable<Float, AnimationVector1D>,
    secondCreeperPaddingBottom: Animatable<Float, AnimationVector1D>,
    isFirstCreeperAnimating: MutableState<Boolean>,
    isSecondCreeperAnimating: MutableState<Boolean>
) {
    val context = LocalContext.current
    val soundPlayer = SoundPlayer(context)

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
fun RenderTnt() {
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
fun RenderTree(onTreeClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp, end = 10.dp)
            .zIndex(10f),
        contentAlignment = Alignment.BottomEnd
    ) {
        Tree(
            modifier = Modifier.size(width = 200.dp, height = 300.dp),
            onClick = onTreeClick
        )
    }
}

@Composable
fun RenderCreeper(
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

@Composable
fun RenderWalkingCreeper(endPosition: Int, isWalking: MutableState<Boolean>, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp, end = endPosition.dp)
            .zIndex(10f),
        contentAlignment = Alignment.BottomEnd
    ) {
        WalkingCreeper(
            modifier = Modifier
                .size(120.dp)
                .zIndex(10f),
            isWalking = isWalking,
            onClick = onClick
        )
    }
}

fun animateCreeperJump(
    scope: CoroutineScope,
    creeperPaddingBottom: Animatable<Float, AnimationVector1D>,
    jumpHeight: Float,
    restingHeight: Float,
    soundPlayer: SoundPlayer? = null,
    isAnimating: MutableState<Boolean>
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
            delay(650)  // Was 500, increased by 30%
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