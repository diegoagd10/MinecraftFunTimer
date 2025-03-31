package com.dagd.minecraftfuntimer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import com.dagd.minecraftfuntimer.ui.scenes.MinecraftTimerScene
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme
import com.dagd.minecraftfuntimer.ui.timer.TimerSetupScreen
import com.dagd.minecraftfuntimer.ui.timer.TimerViewModel

/**
 * Main composable for the Minecraft themed timer application.
 */
@Composable
fun MinecraftTimerApp(
    timerViewModel: TimerViewModel = viewModel(),
    soundPlayer: SoundPlayer? = null
) {
    // Get the current timer state
    val timerState by timerViewModel.timerState.collectAsState()

    // Track whether we're showing the timer setup screen
    var showTimerSetup by remember { mutableStateOf(false) }

    // Track night/day mode
    var isNightMode by remember { mutableStateOf(true) }

    // Create a sound player for ambient sounds if not provided
    val context = LocalContext.current
    val actualSoundPlayer = soundPlayer ?: remember { SoundPlayer(context) }

    // Initial sound playback based on night mode state
    LaunchedEffect(Unit) {
        if (isNightMode) {
            actualSoundPlayer.playNightAmbientSound()
        } else {
            actualSoundPlayer.playDayAmbientSound()
        }
    }

    // Clean up resources when the app is closed (only for internal sound player)
    if (soundPlayer == null) {
        androidx.compose.runtime.DisposableEffect(actualSoundPlayer) {
            onDispose {
                actualSoundPlayer.releaseAll()
            }
        }
    }

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
            onNightModeToggle = {
                // Toggle the night mode state
                isNightMode = !isNightMode

                // Play the appropriate ambient sound based on the new state
                if (isNightMode) {
                    actualSoundPlayer.playNightAmbientSound()
                } else {
                    actualSoundPlayer.playDayAmbientSound()
                }
            },
            onTimerTextClick = {
                // Only show timer setup if timer is not running
                if (!timerState.isRunning) {
                    showTimerSetup = true
                }
            },
            onTimerClick = {
                // Only allow starting the timer
                if (timerState.totalTimeMillis > 0) {
                    if (!timerState.isRunning) {
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