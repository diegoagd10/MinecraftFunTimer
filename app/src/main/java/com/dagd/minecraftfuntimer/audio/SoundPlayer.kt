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
    private var creeperSound: MediaPlayer? = null
    private var cloudSound: MediaPlayer? = null
    private var dayAmbientSound: MediaPlayer? = null
    private var nightAmbientSound: MediaPlayer? = null
    
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
     * Plays the creeper sound
     */
    fun playCreeperSound() {
        releaseCreeperSound()
        creeperSound = MediaPlayer.create(context, R.raw.creeper_sound).apply {
            setOnCompletionListener { mp ->
                mp.release()
                creeperSound = null
            }
            start()
        }
    }
    
    /**
     * Plays the cloud dissolving sound
     */
    fun playCloudSound() {
        releaseCloudSound()
        cloudSound = MediaPlayer.create(context, R.raw.nubes).apply {
            setOnCompletionListener { mp ->
                mp.release()
                cloudSound = null
            }
            start()
        }
    }
    
    /**
     * Plays the day ambient sound (birds)
     * Stops night sound if playing
     */
    fun playDayAmbientSound() {
        // Stop night sound if it's playing
        releaseNightAmbientSound()
        
        // Release any existing day sound
        releaseDayAmbientSound()
        
        // Create and start new day ambient sound
        dayAmbientSound = MediaPlayer.create(context, R.raw.birds).apply {
            isLooping = true
            start()
        }
    }
    
    /**
     * Plays the night ambient sound (crickets)
     * Stops day sound if playing
     */
    fun playNightAmbientSound() {
        // Stop day sound if it's playing
        releaseDayAmbientSound()
        
        // Release any existing night sound
        releaseNightAmbientSound()
        
        // Create and start new night ambient sound
        nightAmbientSound = MediaPlayer.create(context, R.raw.grillos).apply {
            isLooping = true
            start()
        }
    }
    
    /**
     * Stops and releases sound resources
     */
    fun releaseAll() {
        releaseTickingSound()
        releaseExplosionSound()
        releaseCreeperSound()
        releaseCloudSound()
        releaseDayAmbientSound()
        releaseNightAmbientSound()
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
    
    private fun releaseCreeperSound() {
        creeperSound?.apply {
            if (isPlaying) stop()
            release()
        }
        creeperSound = null
    }
    
    private fun releaseCloudSound() {
        cloudSound?.apply {
            if (isPlaying) stop()
            release()
        }
        cloudSound = null
    }
    
    private fun releaseDayAmbientSound() {
        dayAmbientSound?.apply {
            if (isPlaying) stop()
            release()
        }
        dayAmbientSound = null
    }
    
    private fun releaseNightAmbientSound() {
        nightAmbientSound?.apply {
            if (isPlaying) stop()
            release()
        }
        nightAmbientSound = null
    }
} 