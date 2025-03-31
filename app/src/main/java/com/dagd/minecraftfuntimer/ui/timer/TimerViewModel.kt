package com.dagd.minecraftfuntimer.ui.timer

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TimerViewModel : ViewModel() {

    private val _timerState = MutableStateFlow(TimerState())
    val timerState: StateFlow<TimerState> = _timerState.asStateFlow()

    private var countDownTimer: CountDownTimer? = null
    
    // Update interval for smoother animation (100ms = 10 updates per second)
    private val UPDATE_INTERVAL = 100L
    
    // Interface for the CountDownTimer to make testing easier
    internal interface TimerFactory {
        fun createTimer(millisInFuture: Long, countDownInterval: Long, onTick: (Long) -> Unit, onFinish: () -> Unit): CountDownTimer
    }

    // Default implementation that uses the actual Android CountDownTimer
    private val defaultTimerFactory = object : TimerFactory {
        override fun createTimer(
            millisInFuture: Long,
            countDownInterval: Long,
            onTick: (Long) -> Unit,
            onFinish: () -> Unit
        ): CountDownTimer {
            return object : CountDownTimer(millisInFuture, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    onTick(millisUntilFinished)
                }
                override fun onFinish() {
                    onFinish()
                }
            }
        }
    }
    
    // The timer factory to use - can be replaced in tests
    internal var timerFactory: TimerFactory = defaultTimerFactory
    
    // Track the last time we updated the displayed text (we only want to update text every second)
    private var lastTextUpdateTime = 0L

    fun onTimeSelected(hours: Int, minutes: Int, seconds: Int) {
        val totalMillis = (hours * 3600 + minutes * 60 + seconds) * 1000L
        if (totalMillis <= 0) return

        _timerState.update { currentState ->
            currentState.copy(
                totalTimeMillis = totalMillis,
                remainingTimeMillis = totalMillis,
                hours = hours,
                minutes = minutes,
                seconds = seconds,
                isRunning = false,
                progress = 1f,
                timerDisplayText = formatTime(hours, minutes, seconds),
                isCompleted = false
            )
        }
    }

    fun startTimer() {
        val currentState = _timerState.value
        
        if (currentState.remainingTimeMillis <= 0) return
        
        countDownTimer?.cancel()
        
        // Reset the last text update time
        lastTextUpdateTime = currentState.remainingTimeMillis
        
        // Reset completion state when starting a timer
        _timerState.update { it.copy(isCompleted = false) }
        
        countDownTimer = timerFactory.createTimer(
            millisInFuture = currentState.remainingTimeMillis,
            countDownInterval = UPDATE_INTERVAL,
            onTick = { millisUntilFinished ->
                val progress = millisUntilFinished.toFloat() / currentState.totalTimeMillis.toFloat()
                
                // Only update the time components and text if we've crossed a second boundary
                // or if it's the first update
                val shouldUpdateTimeText = millisUntilFinished <= lastTextUpdateTime - 1000 || 
                                         (lastTextUpdateTime - millisUntilFinished) >= 1000
                
                if (shouldUpdateTimeText) {
                    lastTextUpdateTime = millisUntilFinished
                    val timeComponents = calculateTimeComponents(millisUntilFinished)
                    
                    _timerState.update { state ->
                        state.copy(
                            remainingTimeMillis = millisUntilFinished,
                            hours = timeComponents.hours,
                            minutes = timeComponents.minutes,
                            seconds = timeComponents.seconds,
                            progress = progress,
                            isRunning = true,
                            timerDisplayText = formatTime(timeComponents.hours, timeComponents.minutes, timeComponents.seconds)
                        )
                    }
                } else {
                    // Just update the progress for smoother animation
                    _timerState.update { state ->
                        state.copy(
                            remainingTimeMillis = millisUntilFinished,
                            progress = progress,
                            isRunning = true
                        )
                    }
                }
            },
            onFinish = {
                _timerState.update { state ->
                    state.copy(
                        remainingTimeMillis = 0,
                        hours = 0,
                        minutes = 0,
                        seconds = 0,
                        progress = 0f,
                        isRunning = false,
                        isCompleted = true,
                        timerDisplayText = "00:00"
                    )
                }
            }
        ).start()
        
        _timerState.update { it.copy(isRunning = true) }
    }

    fun pauseTimer() {
        // No-op - timer continues running
    }

    fun resetTimer() {
        countDownTimer?.cancel()
        _timerState.update {
            it.copy(
                remainingTimeMillis = it.totalTimeMillis,
                hours = calculateTimeComponents(it.totalTimeMillis).hours,
                minutes = calculateTimeComponents(it.totalTimeMillis).minutes,
                seconds = calculateTimeComponents(it.totalTimeMillis).seconds,
                progress = 1f,
                isRunning = false,
                isCompleted = false,
                timerDisplayText = formatTime(
                    calculateTimeComponents(it.totalTimeMillis).hours, 
                    calculateTimeComponents(it.totalTimeMillis).minutes, 
                    calculateTimeComponents(it.totalTimeMillis).seconds
                )
            )
        }
    }

    private fun calculateTimeComponents(milliseconds: Long): TimeComponents {
        val totalSeconds = milliseconds / 1000
        val hours = (totalSeconds / 3600).toInt()
        val minutes = ((totalSeconds % 3600) / 60).toInt()
        val seconds = (totalSeconds % 60).toInt()
        return TimeComponents(hours, minutes, seconds)
    }

    private fun formatTime(hours: Int, minutes: Int, seconds: Int): String {
        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }

    data class TimeComponents(
        val hours: Int,
        val minutes: Int,
        val seconds: Int
    )
}

data class TimerState(
    val totalTimeMillis: Long = 0,
    val remainingTimeMillis: Long = 0,
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
    val progress: Float = 0f,
    val isRunning: Boolean = false,
    val isCompleted: Boolean = false,
    val timerDisplayText: String = "00:00"
) 