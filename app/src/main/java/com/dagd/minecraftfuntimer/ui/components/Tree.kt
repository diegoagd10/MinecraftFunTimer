package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dagd.minecraftfuntimer.R
import com.dagd.minecraftfuntimer.ui.theme.MinecraftFunTimerTheme

/**
 * Minecraft-themed tree component.
 * Positioning and sizing is delegated to the consumer of this component.
 */
@Composable
fun Tree(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // Tree image
        Image(
            painter = painterResource(id = R.drawable.arbol),
            contentDescription = "Minecraft Tree",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TreePreview() {
    MinecraftFunTimerTheme {
        Tree(
            modifier = Modifier.size(150.dp)
        )
    }
} 