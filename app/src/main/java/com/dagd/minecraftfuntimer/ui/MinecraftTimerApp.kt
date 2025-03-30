package com.dagd.minecraftfuntimer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
        MinecraftTimerScene(
            timerState = timerState,
            isNightMode = isNightMode,
            onNightModeToggle = { isNightMode = !isNightMode },
            onTimerTextClick = {
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
    }
}

@Preview(showBackground = true)
@Composable
fun MinecraftTimerAppPreview() {
    MinecraftFunTimerTheme {
        MinecraftTimerApp()
    }
} 