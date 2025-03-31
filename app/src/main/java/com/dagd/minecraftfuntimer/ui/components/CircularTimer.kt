package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * A circular timer component displaying a pie chart style progress indicator.
 * The component uses Minecraft-themed colors that are attractive for kids.
 *
 * @param modifier The modifier to be applied to the component
 * @param progress The progress of the timer as a value between 0f and 1f
 * @param timeText The text to display below the timer (e.g., "05:00")
 * @param progressContentDescription The accessibility description for the progress
 * @param isRunning Whether the timer is currently running
 * @param isCompleted Whether the timer has completed
 * @param onTimerClick Callback when the timer is clicked
 * @param onTimeTextClick Callback when the time text is clicked
 */
@Composable
fun CircularTimer(
    modifier: Modifier = Modifier,
    progress: Float = 0.75f,
    timeText: String = "05:00",
    progressContentDescription: String = "Timer progress: ${(progress * 100).toInt()}%",
    isRunning: Boolean = false,
    isCompleted: Boolean = false,
    onTimerClick: () -> Unit = {},
    onTimeTextClick: () -> Unit = {}
) {
    // Define Minecraft-themed colors
    val progressColor = Color(0xFF76C922) // Minecraft creeper green for the progress
    val timeTextColor = Color(0xFFFFD700) // Gold/Yellow color for time text - fun and visible
    val completedColor = Color(0xFFFF5722) // Orange/Red for completion state

    // Animation for completion state
    val infiniteTransition = rememberInfiniteTransition(label = "completion")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    // Animate progress color change
    val animatedProgressColor by animateColorAsState(
        targetValue = if (isCompleted) completedColor else progressColor,
        animationSpec = tween(500),
        label = "progressColor"
    )

    // Apply scale animation only when completed
    val timerModifier = if (isCompleted) {
        Modifier.scale(scale)
    } else {
        Modifier
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Box with gray background for the timer (Minecraft stone-like)
        Box(
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .align(Alignment.TopCenter)
                .background(Color(0xFF777777))
                .clickable { onTimerClick() }
                .then(timerModifier),
            contentAlignment = Alignment.Center
        ) {
            // Draw the timer pie chart
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics {
                        contentDescription = progressContentDescription
                    }
            ) {
                // Then, draw the progress as a pie slice
                drawArc(
                    color = animatedProgressColor, // Animated color for the filling
                    startAngle = -90f, // Start from top
                    sweepAngle = 360f * progress, // Angle based on progress
                    useCenter = true, // Fill the pie slice
                )
            }
        }

        // Time text below the circle
        Text(
            text = timeText,
            color = timeTextColor,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .clickable(enabled = !isRunning) { onTimeTextClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularTimerPreview() {
    MinecraftFunTimerTheme {
        CircularTimer(
            modifier = Modifier.size(240.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularTimerCompletedPreview() {
    MinecraftFunTimerTheme {
        CircularTimer(
            modifier = Modifier.size(240.dp),
            isCompleted = true,
            progress = 0f
        )
    }
} 