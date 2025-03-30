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
    }
} 