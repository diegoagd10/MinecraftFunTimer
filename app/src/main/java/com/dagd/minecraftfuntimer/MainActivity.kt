package com.dagd.minecraftfuntimer

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.dagd.minecraftfuntimer.audio.SoundPlayer
import com.dagd.minecraftfuntimer.ui.MinecraftTimerApp
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme
import com.dagd.minecraftfuntimer.ui.timer.TimerViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val timerViewModel: TimerViewModel by viewModels()
    private lateinit var soundPlayer: SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize sound player
        soundPlayer = SoundPlayer(this)
        
        // Configure edge-to-edge display - this tells Android that our app will draw
        // behind the system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Keep screen on while the app is running
        keepScreenOn(window)

        setContent {
            MinecraftFunTimerTheme {
                // Remember a SystemUiController to manage system bars
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = false // Always use light icons for visibility against our dark background
                
                // Update the system bars to be fully transparent
                DisposableEffect(systemUiController, useDarkIcons) {
                    // Make status bar and navigation bar completely transparent
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                    
                    onDispose {}
                }
                
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MinecraftTimerApp(
                        timerViewModel = timerViewModel,
                        soundPlayer = soundPlayer
                    )
                }
            }
        }
    }
    
    override fun onPause() {
        super.onPause()
        // Mute all sounds when app goes to background
        soundPlayer.muteAllSounds()
    }
    
    override fun onResume() {
        super.onResume()
        // Unmute sounds when app comes to foreground
        soundPlayer.unmuteAllSounds()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // Release all sound resources when activity is destroyed
        soundPlayer.releaseAll()
    }

    /**
     * Sets the FLAG_KEEP_SCREEN_ON flag to prevent the screen from turning off
     * while the timer app is running.
     */
    fun keepScreenOn(window: Window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}

@Preview(showBackground = true)
@Composable
fun MinecraftTimerAppPreview() {
    MinecraftFunTimerTheme {
        MinecraftTimerApp()
    }
}