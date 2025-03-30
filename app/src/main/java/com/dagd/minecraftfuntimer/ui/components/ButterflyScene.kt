package com.dagd.minecraftfuntimer.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

/**
 * Renders a group of butterflies at various positions in the scene.
 */
@Composable
fun RenderButterflies() {
    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = 330,
        paddingEnd = 75,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.YELLOW
    )

    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = 330,
        paddingEnd = 140,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.PURPLE
    )

    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = 300,
        paddingEnd = 160,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.BLUE
    )

    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = 270,
        paddingEnd = 160,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.RED
    )

    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = 240,
        paddingEnd = 160,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.ORANGE
    )
} 