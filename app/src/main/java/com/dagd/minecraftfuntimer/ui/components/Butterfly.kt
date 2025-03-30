package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

enum class ButterflyColor {
    YELLOW,
    PURPLE,
    ORANGE,
    RED,
    BLUE
}

@Composable
fun ButterflyPositioned(
    alignment: Alignment,
    paddingBottom: Int = 0,
    paddingStart: Int = 0,
    paddingEnd: Int = 0,
    paddingTop: Int = 0,
    zIndex: Float = 0f,
    size: Int = 30,
    butterflyColor: ButterflyColor = ButterflyColor.YELLOW
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = paddingBottom.dp,
                start = paddingStart.dp,
                end = paddingEnd.dp,
                top = paddingTop.dp
            )
            .zIndex(zIndex),
        contentAlignment = alignment
    ) {
        Butterfly(
            modifier = Modifier
                .size(size.dp)
                .zIndex(zIndex),
            butterflyColor = butterflyColor
        )
    }
}

@Composable
fun Butterfly(
    modifier: Modifier = Modifier,
    butterflyColor: ButterflyColor = ButterflyColor.YELLOW
) {
    val animationDuration = 500
    val rotationAngle = 30f

    val (wingFrontRes, wingBackRes) = when (butterflyColor) {
        ButterflyColor.YELLOW -> Pair(R.drawable.mariposa2, R.drawable.mariposa1)
        ButterflyColor.PURPLE -> Pair(R.drawable.mariposa4, R.drawable.mariposa3)
        ButterflyColor.ORANGE -> Pair(R.drawable.mariposa6, R.drawable.mariposa5)
        ButterflyColor.RED -> Pair(R.drawable.mariposa8, R.drawable.mariposa7)
        ButterflyColor.BLUE -> Pair(R.drawable.mariposa10, R.drawable.mariposa9)
    }

    val rotationAngleClockwise = remember { Animatable(0f) }
    val rotationAngleCounterClockwise = remember { Animatable(0f) }

    // Start animation for front wing (clockwise)
    LaunchedEffect(key1 = "clockwise") {
        animateWingRotation(
            rotationAngle = rotationAngle,
            animationDuration = animationDuration,
            rotationAnimatable = rotationAngleClockwise
        )
    }

    LaunchedEffect(key1 = "counterclockwise") {
        animateWingRotation(
            rotationAngle = -rotationAngle,
            animationDuration = animationDuration,
            rotationAnimatable = rotationAngleCounterClockwise
        )
    }

    // Butterfly container
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Front wing with clockwise rotation
        Image(
            painter = painterResource(id = wingFrontRes),
            contentDescription = "Butterfly front wing",
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotationAngleClockwise.value),
            contentScale = ContentScale.Fit
        )

        // Back wing with counterclockwise rotation
        Image(
            painter = painterResource(id = wingBackRes),
            contentDescription = "Butterfly back wing",
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotationAngleCounterClockwise.value),
            contentScale = ContentScale.Fit
        )
    }
}

private suspend fun animateWingRotation(
    rotationAngle: Float,
    animationDuration: Int,
    rotationAnimatable: Animatable<Float, *>
) {
    while (true) {
        rotationAnimatable.animateTo(
            targetValue = rotationAngle,
            animationSpec = tween(durationMillis = animationDuration)
        )
        rotationAnimatable.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButterflyPreview() {
    MinecraftFunTimerTheme {
        Butterfly(
            modifier = Modifier.size(30.dp),
            butterflyColor = ButterflyColor.YELLOW
        )
    }
} 