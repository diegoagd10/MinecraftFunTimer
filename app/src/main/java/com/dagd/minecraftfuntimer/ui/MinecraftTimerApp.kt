package com.dagd.minecraftfuntimer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme
import com.dagd.minecraftfuntimer.ui.timer.TimerSetupScreen
import com.dagd.minecraftfuntimer.ui.timer.TimerViewModel

/**
 * Main composable for the Minecraft themed timer application.
 */
@Composable
fun MinecraftTimerApp(
    timerViewModel: TimerViewModel = viewModel()
) {
    // Get the current timer state
    val timerState by timerViewModel.timerState.collectAsState()
    
    // Track whether we're showing the timer setup screen
    var showTimerSetup by remember { mutableStateOf(false) }
    
    // Track night/day mode
    var isNightMode by remember { mutableStateOf(true) }
    
    if (showTimerSetup) {
        // Show the timer setup screen
        TimerSetupScreen(
            onTimeConfirmed = { hours, minutes, seconds ->
                timerViewModel.onTimeSelected(hours, minutes, seconds)
                timerViewModel.startTimer()
                showTimerSetup = false
            },
            onCancel = {
                showTimerSetup = false
            }
        )
    } else {
        // Show the main Minecraft timer app
        Box(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            RenderSkyBackground(isNightMode)
            RenderSun(onSunClick = { isNightMode = !isNightMode })
            RenderClouds()
            RenderStars()
            RenderCircularTimer(
                progress = timerState.progress,
                timeText = timerState.timerDisplayText,
                isRunning = timerState.isRunning,
                isCompleted = timerState.isCompleted,
                onTimeTextClick = {
                    // Only show timer setup if timer is not running
                    if (!timerState.isRunning) {
                        showTimerSetup = true
                    }
                },
                onTimerClick = {
                    // Toggle timer state if we have a valid time
                    if (timerState.totalTimeMillis > 0) {
                        if (timerState.isRunning) {
                            timerViewModel.pauseTimer()
                        } else {
                            timerViewModel.startTimer()
                        }
                    } else if (!showTimerSetup) {
                        // If no time is set, show timer setup
                        showTimerSetup = true
                    }
                }
            )
            Ground(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .zIndex(5f)
            )
            RenderMountainSurfaces()
            RenderTnt()
            RenderTree()
            RenderCreeper()
        }
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
            .padding(top = 150.dp),
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
            modifier = Modifier.size(width = 155.dp, height = 100.dp),
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
            modifier = Modifier.size(width = 60.dp, height = 30.dp),
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
            surfaceType = SurfaceType.TYPE_1
        )
    }

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
            surfaceType = SurfaceType.TYPE_2
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
            surfaceType = SurfaceType.TYPE_1
        )
    }

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
            surfaceType = SurfaceType.TYPE_2
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
            .padding(bottom = 50.dp, end = 10.dp)
            .zIndex(10f),
        contentAlignment = Alignment.BottomEnd
    ) {
        Tree(
            modifier = Modifier.size(width = 200.dp, height = 300.dp)
        )
    }
}

@Composable
private fun RenderCreeper() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 35.dp, end = 5.dp)
            .zIndex(10f),
        contentAlignment = Alignment.BottomEnd
    ) {
        Creeper(
            modifier = Modifier.size(120.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MinecraftTimerAppPreview() {
    MinecraftFunTimerTheme {
        MinecraftTimerApp()
    }
} 