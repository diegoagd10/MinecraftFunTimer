package com.dagd.minecraftfuntimer.audio

import android.content.Context
import android.media.MediaPlayer
import com.dagd.minecraftfuntimer.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    private var wingSound: MediaPlayer? = null
    
    private var isMuted = false
    private var ambientSoundJob: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    
    /**
     * Plays the TNT ticking/detonation sound
     */
    fun playTickingSound() {
        if (isMuted) return
        
        releaseTickingSound()
        tickingSound = MediaPlayer.create(context, R.raw.ticking_bomb).apply {
            setVolume(0.1f, 0.1f)
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
        if (isMuted) return
        
        releaseExplosionSound()
        explosionSound = MediaPlayer.create(context, R.raw.a_bomb).apply {
            setVolume(0.1f, 0.1f)
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
        if (isMuted) return
        
        releaseCreeperSound()
        creeperSound = MediaPlayer.create(context, R.raw.creeper_sound).apply {
            setVolume(0.1f, 0.1f)
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
        if (isMuted) return
        
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
     * Plays the day ambient sound (birds) for 5 seconds
     * Stops night sound if playing
     */
    fun playDayAmbientSound() {
        if (isMuted) return
        
        // Cancel any existing ambient sound job
        ambientSoundJob?.cancel()
        
        // Stop night sound if it's playing
        releaseNightAmbientSound()
        
        // Release any existing day sound
        releaseDayAmbientSound()
        
        // Create and start new day ambient sound
        dayAmbientSound = MediaPlayer.create(context, R.raw.birds).apply {
            start()
        }
        
        // Schedule stopping after 5 seconds
        ambientSoundJob = coroutineScope.launch {
            delay(5000) // 5 seconds
            releaseDayAmbientSound()
        }
    }
    
    /**
     * Plays the night ambient sound (crickets) for 5 seconds
     * Stops day sound if playing
     */
    fun playNightAmbientSound() {
        if (isMuted) return
        
        // Cancel any existing ambient sound job
        ambientSoundJob?.cancel()
        
        // Stop day sound if it's playing
        releaseDayAmbientSound()
        
        // Release any existing night sound
        releaseNightAmbientSound()
        
        // Create and start new night ambient sound
        nightAmbientSound = MediaPlayer.create(context, R.raw.grillos).apply {
            start()
        }
        
        // Schedule stopping after 5 seconds
        ambientSoundJob = coroutineScope.launch {
            delay(5000) // 5 seconds
            releaseNightAmbientSound()
        }
    }
    
    /**
     * Plays the butterfly wing sound when tree is clicked
     */
    fun playWingSound() {
        if (isMuted) return
        
        releaseWingSound()
        wingSound = MediaPlayer.create(context, R.raw.wings).apply {
            setOnCompletionListener { mp ->
                mp.release()
                wingSound = null
            }
            start()
        }
    }
    
    /**
     * Mutes all sounds and stops any playing sounds
     */
    fun muteAllSounds() {
        isMuted = true
        releaseAll()
    }
    
    /**
     * Unmutes sounds for future playback
     */
    fun unmuteAllSounds() {
        isMuted = false
    }
    
    /**
     * Stops and releases sound resources
     */
    fun releaseAll() {
        ambientSoundJob?.cancel()
        ambientSoundJob = null
        
        releaseTickingSound()
        releaseExplosionSound()
        releaseCreeperSound()
        releaseCloudSound()
        releaseDayAmbientSound()
        releaseNightAmbientSound()
        releaseWingSound()
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
    
    private fun releaseWingSound() {
        wingSound?.apply {
            if (isPlaying) stop()
            release()
        }
        wingSound = null
    }
} 