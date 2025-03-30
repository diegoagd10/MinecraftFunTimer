package com.dagd.minecraftfuntimer.ui.sound

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.dagd.minecraftfuntimer.audio.SoundPlayer

/**
 * Class to manage sound effects for the app
 */
class SoundEffects(private val context: Context) {
    private val soundPlayer = SoundPlayer(context)
    
    /**
     * Play the butterfly wing sound when tree is clicked
     */
    fun playWingSound() {
        soundPlayer.playWingSound()
    }
    
    /**
     * Clean up resources
     */
    fun release() {
        soundPlayer.releaseAll()
    }
}

/**
 * Composable function to provide the SoundEffects instance
 */
@Composable
fun rememberSoundEffects(): SoundEffects {
    val context = LocalContext.current
    val soundEffects = remember { SoundEffects(context) }
    
    // Clean up when the composable leaves composition
    DisposableEffect(soundEffects) {
        onDispose {
            soundEffects.release()
        }
    }
    
    return soundEffects
} 