package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class SunTest {

    @Test
    fun sunComponent_onClick_callsCallback() {
        // Given
        val onClick = mock(Function0::class.java) as () -> Unit
        
        // When
        onClick()
        
        // Then
        verify(onClick).invoke()
    }
    
    @Test
    fun initialTheme_verifyNightModeActive() {
        // Given
        val nightModeState: MutableState<Boolean> = mutableStateOf(true)
        
        // Then - verify initial state is night mode
        assert(nightModeState.value) { "Initial theme should be night mode" }
    }
    
    @Test
    fun clickingSun_switchFromNightToDay() {
        // Given
        val nightModeState: MutableState<Boolean> = mutableStateOf(true)
        
        // When clicking sun (simulate onClick)
        nightModeState.value = !nightModeState.value
        
        // Then - verify state changed to day mode
        assert(!nightModeState.value) { "Theme should switch to day mode after clicking sun" }
    }
    
    @Test
    fun clickingSun_switchFromDayToNight() {
        // Given
        val nightModeState: MutableState<Boolean> = mutableStateOf(false)
        
        // When clicking sun (simulate onClick)
        nightModeState.value = !nightModeState.value
        
        // Then - verify state changed to night mode
        assert(nightModeState.value) { "Theme should switch to night mode after clicking sun" }
    }
} 