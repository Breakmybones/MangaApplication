package com.example.myap.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.graphics.Shape

data class MangaColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color,
)

data class MangaTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val caption: TextStyle,
    val manga: TextStyle
)

data class MangaShape(
    val padding: Dp,
    val cornersStyle: Shape
)

data class MangaImages(
    val id: Int,
    val contentDesc: String
)

object MangaTheme {
    val colors: MangaColors
        @Composable
        get() = LocalMangaColors.current

    val typography: MangaTypography
        @Composable
        get() = LocalMangaTypography.current

    val shapes: MangaShape
        @Composable
        get() = LocalMangaShape.current
}

enum class MangaStyle {
    Purple, Orange, Blue, Red, Green
}

enum class MangaSize {
    Small, Medium, Big
}

enum class MangaCorners {
    Flat, Rounded
}

val LocalMangaColors = staticCompositionLocalOf<MangaColors> {
    error("No colors provided")
}

val LocalMangaTypography = staticCompositionLocalOf<MangaTypography> {
    error("No font provided")
}

val LocalMangaShape = staticCompositionLocalOf<MangaShape> {
    error("No shapes provided")
}
