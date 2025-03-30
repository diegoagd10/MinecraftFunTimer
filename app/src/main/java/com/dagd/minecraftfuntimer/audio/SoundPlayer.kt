package com.dagd.minecraftfuntimer.audio

import android.content.Context
import android.media.MediaPlayer
import com.dagd.minecraftfuntimer.R

/**
 * Handles sound playback for game elements
 */
class SoundPlayer(private val context: Context) {
    
    private var tickingSound: MediaPlayer? = null
    private var explosionSound: MediaPlayer? = null
    
    /**
     * Plays the TNT ticking/detonation sound
     */
    fun playTickingSound() {
        releaseTickingSound()
        tickingSound = MediaPlayer.create(context, R.raw.ticking_bomb).apply {
            setOnCompletionListener { mp ->
                mp.release()
                tickingSound = null
            }
            start()
        }
    }
    
    /**
     * Plays the TNT explosion sound
     */
    fun playExplosionSound() {
        releaseExplosionSound()
        explosionSound = MediaPlayer.create(context, R.raw.a_bomb).apply {
            setOnCompletionListener { mp ->
                mp.release()
                explosionSound = null
            }
            start()
        }
    }
    
    /**
     * Stops and releases sound resources
     */
    fun releaseAll() {
        releaseTickingSound()
        releaseExplosionSound()
    }
    
    private fun releaseTickingSound() {
        tickingSound?.apply {
            if (isPlaying) stop()
            release()
        }
        tickingSound = null
    }
    
    private fun releaseExplosionSound() {
        explosionSound?.apply {
            if (isPlaying) stop()
            release()
        }
        explosionSound = null
    }
} 