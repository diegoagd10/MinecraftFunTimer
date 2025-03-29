package com.dagd.minecraftfuntimer.ui.sound

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.dagd.minecraftfuntimer.R

/**
 * Class to manage sound effects for the app
 */
class SoundEffects(private val context: Context) {
    private var wingSound: MediaPlayer? = null
    
    /**
     * Play the butterfly wing sound when tree is clicked
     */
    fun playWingSound() {
        // Release any existing sound
        wingSound?.release()
        
        // Create and start a new instance
        wingSound = MediaPlayer.create(context, R.raw.wings)
        wingSound?.start()
        
        // Set a listener to release resources when playback completes
        wingSound?.setOnCompletionListener { player ->
            player.release()
        }
    }
    
    /**
     * Clean up resources
     */
    fun release() {
        wingSound?.release()
        wingSound = null
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