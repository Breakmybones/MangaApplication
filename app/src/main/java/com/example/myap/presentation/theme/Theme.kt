package com.example.myap.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.example.myap.presentation.theme.*

@Composable
fun MangaTheme(
    style: MangaStyle = MangaStyle.Purple,
    textSize: MangaSize = MangaSize.Medium,
    paddingSize: MangaSize = MangaSize.Medium,
    corners: MangaCorners = MangaCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    fontFamily: FontFamily = FontFamily.Default,
    content: @Composable () -> Unit
) {
    val colors = when {
        darkTheme -> {
            when (style) {
                MangaStyle.Purple -> purpleDarkPalette
                MangaStyle.Blue -> blueDarkPalette
                MangaStyle.Orange -> orangeDarkPalette
                MangaStyle.Red -> redDarkPalette
                MangaStyle.Green -> greenDarkPalette
            }
        }

        else -> {
            when (style) {
                MangaStyle.Purple -> purpleLightPalette
                MangaStyle.Blue -> blueLightPalette
                MangaStyle.Orange -> orangeLightPalette
                MangaStyle.Red -> redLightPalette
                MangaStyle.Green -> greenLightPalette
            }
        }
    }

    val typography = MangaTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                MangaSize.Small -> 24.sp
                MangaSize.Medium -> 28.sp
                MangaSize.Big -> 32.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                MangaSize.Small -> 14.sp
                MangaSize.Medium -> 16.sp
                MangaSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontFamily = FontFamily.Cursive,
            fontSize = when (textSize) {
                MangaSize.Small -> 14.sp
                MangaSize.Medium -> 16.sp
                MangaSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontSize = when (textSize) {
                MangaSize.Small -> 10.sp
                MangaSize.Medium -> 12.sp
                MangaSize.Big -> 14.sp
            }
        ),
        manga = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
        )
    )

    val shapes = MangaShape(
        padding = when (paddingSize) {
            MangaSize.Small -> 12.dp
            MangaSize.Medium -> 16.dp
            MangaSize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            MangaCorners.Flat -> RoundedCornerShape(0.dp)
            MangaCorners.Rounded -> RoundedCornerShape(8.dp)
        }
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primaryBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalMangaColors provides colors,
        LocalMangaTypography provides typography,
        LocalMangaShape provides shapes,
        content = content
    )
}
