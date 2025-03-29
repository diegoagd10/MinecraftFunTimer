package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
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
 */
@Composable
fun CircularTimer(
    modifier: Modifier = Modifier,
    progress: Float = 0.75f,
    timeText: String = "05:00",
    progressContentDescription: String = "Timer progress: ${(progress * 100).toInt()}%"
) {
    // Define Minecraft-themed colors
    val lightGray = Color(0xFFAAAAAA) // Light gray for the background
    val darkGray = Color(0xFF555555) // Dark gray for the border
    val progressColor = Color(0xFF76C922) // Minecraft creeper green for the progress
    val timeTextColor = Color(0xFFFFD700) // Gold/Yellow color for time text - fun and visible

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
                .background(Color(0xFF777777)),
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
                    color = progressColor, // Minecraft green for the filling
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