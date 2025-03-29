package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.ui.sound.rememberSoundEffects
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme
import kotlinx.coroutines.delay

/**
 * Minecraft-themed tree component with butterflies animation on click.
 * This recreates the functionality of the original HTML/CSS implementation.
 */
@Composable
fun Tree(
    modifier: Modifier = Modifier,
    onTreeClick: () -> Unit = {}
) {
    // Get screen dimensions to calculate relative positions
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    
    // Sound effects handler
    val soundEffects = rememberSoundEffects()
    
    // State to track if the tree has been clicked (to trigger butterflies)
    var isTreeClicked by remember { mutableStateOf(false) }
    
    // Press effect state (simulating hover on mobile)
    var isPressed by remember { mutableStateOf(false) }
    
    // Responsive tree size calculation - scale based on screen size and aspect ratio
    val screenAspectRatio = screenWidth.toFloat() / screenHeight.toFloat()
    val isTablet = screenAspectRatio < 0.7f || screenWidth > 900 // Tablets typically have aspect ratios closer to 4:3
    
    // Calculate tree size as a percentage of screen width for better proportions across devices
    val treeSizePercent = if (isTablet) 0.18f else 0.20f // Smaller for tablets to fit better
    val treeSize = (screenWidth * treeSizePercent).coerceIn(100f, 250f)  // Adjusted max size
    
    // Position tree on right side of screen with different calculations for tablets vs phones
    val horizontalPosition = if (isTablet) {
        // For tablets: Position more centered on the right half of the screen
        val screenCenterX = screenWidth / 2f
        val rightShift = screenWidth * 0.2f // 20% right from center
        screenCenterX + rightShift
    } else {
        // For phones: Position 65% across the screen (more to the right)
        screenWidth * 0.65f
    }
    
    // Position the tree so it's partially planted in the ground
    val groundHeight = screenHeight * 0.145f  // From Ground.kt
    val groundTop = screenHeight - groundHeight
    
    // Place tree with its base partially embedded in the ground
    // We position the tree higher for tablets to match the reference image
    val embeddedPercent = if (isTablet) 0.2f else 0.3f
    val treeBottomPosition = groundTop - treeSize * (1 - embeddedPercent)
    
    // Tree press animation (simulating hover on mobile)
    val treeOffsetY by animateFloatAsState(
        targetValue = if (isPressed) -2f else 0f,
        label = "treePressAnimation"
    )
    
    Box(modifier = modifier) {
        // Tree image
        Image(
            painter = painterResource(id = R.drawable.arbol),
            contentDescription = "Minecraft Tree",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .zIndex(20f)  // Much higher z-index to ensure tree is in front of the grass
                .offset(
                    x = horizontalPosition.dp,
                    y = (treeBottomPosition + treeOffsetY).dp
                )
                .size(treeSize.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { 
                            isPressed = true
                            tryAwaitRelease()
                            isPressed = false
                        },
                        onTap = {
                            isTreeClicked = true
                            
                            // Play sound when tree is clicked
                            soundEffects.playWingSound()
                            
                            onTreeClick()
                        }
                    )
                }
        )
        
        // Butterflies animated when tree is clicked
        if (isTreeClicked) {
            // Scale butterfly size relative to tree size
            val butterflySize = (treeSize * 0.12f).coerceIn(12f, 36f)
            
            // Butterfly 1 - up and left
            ButterflyAnimation(
                startX = horizontalPosition + treeSize * 0.05f,
                startY = treeBottomPosition,
                endX = horizontalPosition - treeSize * 0.8f,
                endY = treeBottomPosition - treeSize * 1.5f,
                butterflyImage1 = R.drawable.mariposa1,
                butterflyImage2 = R.drawable.mariposa2,
                butterflySize = butterflySize
            )
            
            // Butterfly 2 - up and slightly left
            ButterflyAnimation(
                startX = horizontalPosition + treeSize * 0.15f,
                startY = treeBottomPosition + 5f,
                endX = horizontalPosition - treeSize * 0.4f,
                endY = treeBottomPosition - treeSize * 1.2f,
                butterflyImage1 = R.drawable.mariposa3,
                butterflyImage2 = R.drawable.mariposa4,
                butterflySize = butterflySize,
                delayStart = 100
            )
            
            // Butterfly 3 - up and right
            ButterflyAnimation(
                startX = horizontalPosition,
                startY = treeBottomPosition - treeSize * 0.1f,
                endX = horizontalPosition + treeSize * 0.6f,
                endY = treeBottomPosition - treeSize * 1.4f,
                butterflyImage1 = R.drawable.mariposa5,
                butterflyImage2 = R.drawable.mariposa4,
                butterflySize = butterflySize,
                delayStart = 200
            )
            
            // Reset the click state after animation completes
            LaunchedEffect(isTreeClicked) {
                delay(1500) // Animation duration
                isTreeClicked = false
            }
        }
    }
}

/**
 * Composable that animates a butterfly from start position to end position
 * with wing flapping animation.
 */
@Composable
private fun ButterflyAnimation(
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float,
    butterflyImage1: Int,
    butterflyImage2: Int,
    butterflySize: Float = 16f,
    delayStart: Int = 0
) {
    // Animation for butterfly path
    var animationProgress by remember { mutableFloatStateOf(0f) }
    val posX = startX + (endX - startX) * animationProgress
    val posY = startY + (endY - startY) * animationProgress
    
    // Animation for wing flapping (alternating between two images)
    var wingFrame by remember { mutableIntStateOf(0) }
    val butterflyImage = if (wingFrame == 0) butterflyImage1 else butterflyImage2
    
    // Flap wings by alternating between images
    LaunchedEffect(Unit) {
        delay(delayStart.toLong())
        
        while (true) {
            wingFrame = (wingFrame + 1) % 2
            delay(150) // Wing flap speed
        }
    }
    
    // Update position animation progress
    LaunchedEffect(Unit) {
        // Wait for any start delay
        delay(delayStart.toLong())
        
        // Animate from 0 to 1 over 1.5 seconds
        val animationDuration = 1500L
        val startTime = System.currentTimeMillis()
        
        while (animationProgress < 1f) {
            val currentTime = System.currentTimeMillis()
            animationProgress = ((currentTime - startTime) / animationDuration.toFloat())
                .coerceIn(0f, 1f)
            delay(16) // Roughly 60fps
        }
    }
    
    // Butterfly image
    Image(
        painter = painterResource(id = butterflyImage),
        contentDescription = "Butterfly",
        modifier = Modifier
            .zIndex(30f)  // Even higher z-index for butterflies
            .offset(x = posX.dp, y = posY.dp)
            .size(butterflySize.dp) // Scaled butterfly size
            .alpha((1f - animationProgress * 0.7f).coerceIn(0.3f, 1f)) // Fade out as it flies away
    )
}

@Preview(showBackground = true)
@Composable
fun TreePreview() {
    MinecraftFunTimerTheme {
        Box(modifier = Modifier.size(300.dp)) {
            Tree()
        }
    }
} 