package com.dagd.minecraftfuntimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dagd.minecraftfuntimer.ui.MinecraftTimerApp
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme
import com.dagd.minecraftfuntimer.ui.timer.TimerViewModel

class MainActivity : ComponentActivity() {
    
    private val timerViewModel: TimerViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinecraftFunTimerTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                ) {
                    MinecraftTimerApp(timerViewModel = timerViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MinecraftTimerAppPreview() {
    MinecraftFunTimerTheme {
        MinecraftTimerApp()
    }
}