package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Minecraft-themed Walking Creeper component.
 * Displays a creeper with head, body and feet for a walking animation.
 */
@Composable
fun WalkingCreeper(
    modifier: Modifier = Modifier,
    isWalking: MutableState<Boolean> = remember { mutableStateOf(false) },
    onClick: () -> Unit = {}
) {
    val (movingLeftFoot, movingRightFoot, movingBackFoot) = animateFeet(isWalking)

    Box(
        modifier = modifier.clickable(onClick = onClick),
    ) {
        // Creeper head
        Image(
            painter = painterResource(id = R.drawable.cabeza_shon),
            contentDescription = "Creeper Head",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(width = 43.dp, height = 34.dp)
                .zIndex(2f),
            contentScale = ContentScale.FillBounds
        )

        // Creeper body
        Image(
            painter = painterResource(id = R.drawable.cuerpo_shon1),
            contentDescription = "Creeper Body",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .size(width = 40.dp, height = 77.dp)
                .zIndex(2f),
            contentScale = ContentScale.FillBounds
        )

        // Creeper feet
        Image(
            painter = painterResource(id = R.drawable.patita1),
            contentDescription = "Creeper Left Foot",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 27.dp)
                .size(width = 28.dp, height = 37.dp)
                .zIndex(3f)
                .rotate(movingLeftFoot.value),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            painter = painterResource(id = R.drawable.patita2),
            contentDescription = "Creeper Right Foot",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 37.dp)
                .size(width = 28.dp, height = 37.dp)
                .zIndex(3f)
                .rotate(movingRightFoot.value),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            painter = painterResource(id = R.drawable.patita3),
            contentDescription = "Creeper Back Foot",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 3.dp)
                .size(width = 28.dp, height = 37.dp)
                .zIndex(1f)
                .rotate(movingBackFoot.value),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun animateFeet(isWalking: MutableState<Boolean>): Triple<Animatable<Float, AnimationVector1D>,
        Animatable<Float, AnimationVector1D>,
        Animatable<Float, AnimationVector1D>> {
    val leftFoot = remember { Animatable(0f) }
    val rightFoot = remember { Animatable(0f) }
    val backFoot = remember { Animatable(0f) }

    LaunchedEffect(key1 = isWalking.value) {
        if (isWalking.value) {
            animateFeetMovement(angle = 10f, animationSpeed = 250, leftFoot)
        } else {
            leftFoot.animateTo(0f)
        }
    }

    LaunchedEffect(key1 = isWalking.value) {
        if (isWalking.value) {
            animateFeetMovement(angle = 10f, animationSpeed = 270, rightFoot)
        } else {
            rightFoot.animateTo(0f)
        }
    }

    LaunchedEffect(key1 = isWalking.value) {
        if (isWalking.value) {
            animateFeetMovement(angle = 15f, animationSpeed = 300, backFoot)
        } else {
            backFoot.animateTo(0f)
        }
    }

    return Triple(leftFoot, rightFoot, backFoot)
}

private suspend fun animateFeetMovement(
    angle: Float,
    animationSpeed: Int,
    movingFeet: Animatable<Float, AnimationVector1D>
) {
    while (true) {
        movingFeet.animateTo(
            targetValue = angle,
            animationSpec = tween(durationMillis = animationSpeed)
        )
        movingFeet.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = animationSpeed)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WalkingCreeperPreview() {
    MinecraftFunTimerTheme {
        WalkingCreeper(
            modifier = Modifier.size(120.dp)
        )
    }
} 