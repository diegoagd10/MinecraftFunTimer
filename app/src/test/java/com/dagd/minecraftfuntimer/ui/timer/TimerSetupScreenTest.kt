package com.dagd.minecraftfuntimer.ui.timer

import org.junit.Assert.assertEquals
import org.junit.Test

class TimerSetupScreenTest {

    @Test
    fun `formatTimeDisplay should show correct minutes`() {
        // Test cases
        val testCases = mapOf(
            0 to "0 minutes",
            1 to "1 minute",
            5 to "5 minutes",
            60 to "1 hour 0 minutes",
            65 to "1 hour 5 minutes",
            120 to "2 hours 0 minutes",
            125 to "2 hours 5 minutes"
        )
        
        // Test each case directly using the utility class
        testCases.forEach { (minutes, expected) ->
            val result = TimerFormatUtils.formatTimeDisplay(minutes)
            assertEquals("For $minutes minutes", expected, result)
        }
    }
} 