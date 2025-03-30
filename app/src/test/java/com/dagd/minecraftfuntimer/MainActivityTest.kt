package com.dagd.minecraftfuntimer

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class MainActivityScreenFlagsTest {
    
    @Test
    fun shouldAddFlagToWindowWhenKeepScreenOnIsCalled() {
        // Arrange
        val window = mock(Window::class.java)
        
        // Act - directly test the functionality, not the implementation
        val mainActivity = MainActivity()
        mainActivity.keepScreenOn(window)
        
        // Assert
        verify(window).addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
    
    @Test
    fun shouldSetKeepScreenOnFlagInOnCreate() {
        // Use Robolectric to create an actual MainActivity instance
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        
        // Check that the window has the KEEP_SCREEN_ON flag set
        val flags = activity.window.attributes.flags
        assert(flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON != 0) {
            "FLAG_KEEP_SCREEN_ON should be set in onCreate"
        }
    }
} 