package com.dagd.minecraftfuntimer.ui.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

@Composable
fun TimerSetupScreen(
    onTimeConfirmed: (hours: Int, minutes: Int, seconds: Int) -> Unit,
    onCancel: () -> Unit
) {
    // State to track the current minutes value
    var minutes by remember { mutableStateOf("") }

    // Background color for the screen
    val backgroundColor = Color(0xFF121212)
    
    // Colors for the digit buttons
    val digitButtonColor = Color(0xFF2E2E2E)
    val digitTextColor = Color.White
    val runButtonColor = Color(0xFF76C922) // Minecraft green

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        
        // Time display - simple minutes format
        Text(
            text = "Set Timer Minutes",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Minutes value display
        Text(
            text = if (minutes.isEmpty()) "0" else minutes,
            fontSize = 60.sp,
            color = Color(0xFFFFD700), // Gold color for time text
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // Show time in readable format
        if (minutes.isNotEmpty()) {
            val mins = minutes.toIntOrNull() ?: 0
            Text(
                text = formatTimeDisplay(mins),
                fontSize = 20.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
        }
        
        Spacer(modifier = Modifier.weight(0.1f))
        
        // Number pad
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First row: 1, 2, 3
            NumberRow(
                numbers = listOf(1, 2, 3),
                digitButtonColor = digitButtonColor,
                digitTextColor = digitTextColor,
                onDigitClick = { digit -> 
                    // Allow reasonable timer limits (0-999 minutes)
                    val newValue = minutes + digit.toString()
                    if (newValue.length <= 3 && (newValue.toIntOrNull() ?: 0) <= 999) {
                        minutes = newValue
                    }
                }
            )
            
            // Second row: 4, 5, 6
            NumberRow(
                numbers = listOf(4, 5, 6),
                digitButtonColor = digitButtonColor,
                digitTextColor = digitTextColor,
                onDigitClick = { digit -> 
                    val newValue = minutes + digit.toString()
                    if (newValue.length <= 3 && (newValue.toIntOrNull() ?: 0) <= 999) {
                        minutes = newValue
                    }
                }
            )
            
            // Third row: 7, 8, 9
            NumberRow(
                numbers = listOf(7, 8, 9),
                digitButtonColor = digitButtonColor,
                digitTextColor = digitTextColor,
                onDigitClick = { digit -> 
                    val newValue = minutes + digit.toString()
                    if (newValue.length <= 3 && (newValue.toIntOrNull() ?: 0) <= 999) {
                        minutes = newValue
                    }
                }
            )
            
            // Fourth row: 0, backspace
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 0 button
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(digitButtonColor)
                        .clickable {
                            if (minutes.isNotEmpty()) {
                                val newValue = minutes + "0"
                                if (newValue.length <= 3 && (newValue.toIntOrNull() ?: 0) <= 999) {
                                    minutes = newValue
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "0",
                        fontSize = 24.sp,
                        color = digitTextColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                // Backspace button
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(digitButtonColor)
                        .clickable {
                            if (minutes.isNotEmpty()) {
                                minutes = minutes.dropLast(1)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "⌫",
                        fontSize = 24.sp,
                        color = digitTextColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.weight(0.1f))
        
        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Cancel button
            Button(
                onClick = { onCancel() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Start button
            Button(
                onClick = {
                    val mins = minutes.toIntOrNull() ?: 0
                    onTimeConfirmed(0, mins, 0)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = runButtonColor
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "Start",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(0.05f))
    }
}

@Composable
private fun NumberRow(
    numbers: List<Int>,
    digitButtonColor: Color,
    digitTextColor: Color,
    onDigitClick: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        numbers.forEach { number ->
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(digitButtonColor)
                    .clickable { onDigitClick(number) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = number.toString(),
                    fontSize = 24.sp,
                    color = digitTextColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Helper object to make functions testable
object TimerFormatUtils {
    // Format minutes into a readable time display
    fun formatTimeDisplay(minutes: Int): String {
        val hours = minutes / 60
        val mins = minutes % 60
        
        return if (hours > 0) {
            "$hours hour${if (hours > 1) "s" else ""} $mins minute${if (mins != 1) "s" else ""}"
        } else {
            "$mins minute${if (mins != 1) "s" else ""}"
        }
    }
}

// Use the extracted function within the composable
private fun formatTimeDisplay(minutes: Int): String {
    return TimerFormatUtils.formatTimeDisplay(minutes)
}

@Preview(showBackground = true)
@Composable
fun TimerSetupScreenPreview() {
    MinecraftFunTimerTheme {
        TimerSetupScreen(
            onTimeConfirmed = { _, _, _ -> },
            onCancel = { }
        )
    }
} 