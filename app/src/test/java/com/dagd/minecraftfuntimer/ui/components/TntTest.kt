package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.runtime.mutableStateOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TntTest {
    
    @Test
    fun `when tnt is not detonated, state should be idle`() {
        // Arrange
        val tntState = TntState()
        
        // Assert
        assertEquals(TntDetonationState.IDLE, tntState.detonationState.value)
        assertFalse(tntState.isExploding.value)
        assertTrue(tntState.isVisible.value)
        assertEquals(0f, tntState.flashAlpha.value)
    }
    
    @Test
    fun `when detonate is called, state should change to detonating`() {
        // Arrange
        val tntState = TntState()
        
        // Act
        tntState.detonate()
        
        // Assert
        assertEquals(TntDetonationState.DETONATING, tntState.detonationState.value)
        assertFalse(tntState.isExploding.value)
        assertTrue(tntState.isVisible.value)
    }
    
    @Test
    fun `when explosion completes, tnt should disappear`() {
        // Arrange
        val tntState = TntState()
        
        // Act
        tntState.detonate()
        tntState.explode()
        
        // Assert
        assertEquals(TntDetonationState.EXPLODED, tntState.detonationState.value)
        assertTrue(tntState.isExploding.value)
        assertFalse(tntState.isVisible.value)
    }
    
    @Test
    fun `after explosion animation completes, tnt should reset to idle state`() {
        // Arrange
        val tntState = TntState()
        
        // Act
        tntState.detonate()
        tntState.explode()
        tntState.reset()
        
        // Assert
        assertEquals(TntDetonationState.IDLE, tntState.detonationState.value)
        assertFalse(tntState.isExploding.value)
        assertTrue(tntState.isVisible.value)
        assertEquals(0f, tntState.flashAlpha.value)
    }
    
    @Test
    fun `after reset, flash alpha should be reset to zero`() {
        // Arrange
        val tntState = TntState()
        
        // Act - simulate a full explosion cycle
        tntState.detonate()
        // Manually modify the flash alpha value to simulate animation
        tntState.flashAlpha.value = 0.8f
        tntState.explode()
        tntState.reset()
        
        // Assert
        assertEquals(0f, tntState.flashAlpha.value)
    }
} 