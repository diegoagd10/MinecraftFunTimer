package com.dagd.minecraftfuntimer.ui.timer

import org.junit.Assert.assertEquals
import org.junit.Test

class TimerFormatUtilsTest {

    @Test
    fun `formatTimeDisplay with single minute returns singular form`() {
        // When
        val result = TimerFormatUtils.formatTimeDisplay(1)
        
        // Then
        assertEquals("1 minute", result)
    }

    @Test
    fun `formatTimeDisplay with multiple minutes returns plural form`() {
        // When
        val result = TimerFormatUtils.formatTimeDisplay(5)
        
        // Then
        assertEquals("5 minutes", result)
    }
    
    @Test
    fun `formatTimeDisplay with zero minutes returns plural form`() {
        // When
        val result = TimerFormatUtils.formatTimeDisplay(0)
        
        // Then
        assertEquals("0 minutes", result)
    }
    
    @Test
    fun `formatTimeDisplay with one hour and zero minutes formats correctly`() {
        // When
        val result = TimerFormatUtils.formatTimeDisplay(60)
        
        // Then
        assertEquals("1 hour 0 minutes", result)
    }
    
    @Test
    fun `formatTimeDisplay with one hour and one minute formats correctly`() {
        // When
        val result = TimerFormatUtils.formatTimeDisplay(61)
        
        // Then
        assertEquals("1 hour 1 minute", result)
    }
    
    @Test
    fun `formatTimeDisplay with multiple hours and minutes formats correctly`() {
        // When
        val result = TimerFormatUtils.formatTimeDisplay(125) // 2 hours, 5 minutes
        
        // Then
        assertEquals("2 hours 5 minutes", result)
    }
} 