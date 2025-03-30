package com.dagd.minecraftfuntimer.ui.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
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

    // UI Colors
    val backgroundColor = Color(0xFF121212)
    val digitButtonColor = Color(0xFF2E2E2E)
    val digitTextColor = Color.White
    val runButtonColor = Color(0xFF76C922) // Minecraft green
    val timeDisplayColor = Color(0xFFFFD700) // Gold color

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
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
            color = timeDisplayColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // Show time in readable format
        if (minutes.isNotEmpty()) {
            val mins = minutes.toIntOrNull() ?: 0
            Text(
                text = TimerFormatUtils.formatTimeDisplay(mins),
                fontSize = 20.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
        }
        
        Spacer(modifier = Modifier.weight(0.1f))
        
        // Number pad with extracted component
        NumberPad(
            currentValue = minutes,
            onDigitClick = { digit -> 
                if (canAddDigit(minutes, digit)) {
                    minutes += digit.toString()
                }
            },
            onBackspace = {
                if (minutes.isNotEmpty()) {
                    minutes = minutes.dropLast(1)
                }
            },
            digitButtonColor = digitButtonColor,
            digitTextColor = digitTextColor
        )
        
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
private fun NumberPad(
    currentValue: String,
    onDigitClick: (Int) -> Unit,
    onBackspace: () -> Unit,
    digitButtonColor: Color,
    digitTextColor: Color
) {
    // Calculate which buttons should be disabled based on current value
    val isDigitDisabled = { digit: Int ->
        if (currentValue.isEmpty()) {
            // Only disable 0 when empty (handled separately in the 0 button)
            false
        } else if (currentValue.length == 1) {
            // On second digit, disable buttons that would make the total > 60
            val firstDigit = currentValue.toIntOrNull() ?: 0
            if (firstDigit == 6) {
                // If first digit is 6, only allow 0 as second digit
                digit > 0
            } else if (firstDigit > 6) {
                // If first digit is > 6, disable all second digits
                true
            } else {
                // First digit is <= 5, allow all second digits
                false
            }
        } else {
            // Already have 2 digits, don't allow more
            true
        }
    }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // First row: 1, 2, 3
        NumberRow(
            numbers = listOf(1, 2, 3),
            digitButtonColor = digitButtonColor,
            digitTextColor = digitTextColor,
            onDigitClick = onDigitClick,
            isDisabled = isDigitDisabled
        )
        
        // Second row: 4, 5, 6
        NumberRow(
            numbers = listOf(4, 5, 6),
            digitButtonColor = digitButtonColor,
            digitTextColor = digitTextColor,
            onDigitClick = onDigitClick,
            isDisabled = isDigitDisabled
        )
        
        // Third row: 7, 8, 9
        NumberRow(
            numbers = listOf(7, 8, 9),
            digitButtonColor = digitButtonColor,
            digitTextColor = digitTextColor,
            onDigitClick = onDigitClick,
            isDisabled = isDigitDisabled
        )
        
        // Fourth row: 0, backspace
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 0 button - disabled when there are no other digits or when it would make the value > 60
            NumberButton(
                number = 0,
                onClick = { onDigitClick(0) },
                backgroundColor = digitButtonColor,
                textColor = digitTextColor,
                isDisabled = currentValue.isEmpty() || isDigitDisabled(0)
            )
            
            // Backspace button
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(digitButtonColor)
                    .clickable { onBackspace() },
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
}

@Composable
private fun NumberRow(
    numbers: List<Int>,
    digitButtonColor: Color,
    digitTextColor: Color,
    onDigitClick: (Int) -> Unit,
    isDisabled: (Int) -> Boolean
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        numbers.forEach { number ->
            NumberButton(
                number = number,
                onClick = { onDigitClick(number) },
                backgroundColor = digitButtonColor,
                textColor = digitTextColor,
                isDisabled = isDisabled(number)
            )
        }
    }
}

@Composable
private fun NumberButton(
    number: Int,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color,
    isDisabled: Boolean = false
) {
    val buttonColor = if (isDisabled) backgroundColor.copy(alpha = 0.3f) else backgroundColor
    val textColorWithAlpha = if (isDisabled) textColor.copy(alpha = 0.3f) else textColor
    
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(buttonColor)
            .clickable(enabled = !isDisabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            fontSize = 24.sp,
            color = textColorWithAlpha,
            fontWeight = FontWeight.Bold
        )
    }
}

// Helper function to validate input
private fun canAddDigit(currentValue: String, digit: Int): Boolean {
    // Don't allow leading zeros
    if (currentValue.isEmpty() && digit == 0) {
        return false
    }
    
    val newValue = currentValue + digit.toString()
    val newValueInt = newValue.toIntOrNull() ?: 0
    
    // Limit to 60 minutes
    return newValue.length <= 2 && newValueInt <= 60
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