package com.dagd.minecraftfuntimer.ui.scenes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.ui.components.CircularTimer

@Composable
fun RenderCircularTimer(
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