package com.dagd.minecraftfuntimer.ui.scenes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.dagd.minecraftfuntimer.ui.components.ButterflyColor
import com.dagd.minecraftfuntimer.ui.components.ButterflyPositioned

/**
 * Renders a group of butterflies at various positions in the scene.
 */
@Composable
fun RenderButterflies(
    yellowButterflyPaddingBottom: Int = 440,
    purpleButterflyPaddingBottom: Int = 330,
    purpleButterflyPaddingEnd: Int = 140
) {
    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = yellowButterflyPaddingBottom,
        paddingEnd = 75,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.YELLOW
    )

    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = purpleButterflyPaddingBottom,
        paddingEnd = purpleButterflyPaddingEnd,
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