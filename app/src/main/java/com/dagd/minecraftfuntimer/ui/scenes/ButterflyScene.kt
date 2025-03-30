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
    purpleButterflyPaddingEnd: Int = 140,
    blueButterflyPaddingBottom: Int = 300,
    blueButterflyPaddingEnd: Int = 160,
    redButterflyPaddingBottom: Int = 270,
    redButterflyPaddingEnd: Int = 160,
    orangeButterflyPaddingEnd: Int = 160
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
        paddingBottom = blueButterflyPaddingBottom,
        paddingEnd = blueButterflyPaddingEnd,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.BLUE
    )

    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = redButterflyPaddingBottom,
        paddingEnd = redButterflyPaddingEnd,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.RED
    )

    ButterflyPositioned(
        alignment = Alignment.BottomEnd,
        paddingBottom = 240,
        paddingEnd = orangeButterflyPaddingEnd,
        zIndex = 9f,
        size = 30,
        butterflyColor = ButterflyColor.ORANGE
    )
} 