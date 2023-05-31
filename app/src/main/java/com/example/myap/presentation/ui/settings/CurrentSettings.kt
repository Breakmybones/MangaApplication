package com.example.myap.presentation.ui.settings

import com.example.myap.presentation.theme.MangaCorners
import com.example.myap.presentation.theme.MangaSize
import com.example.myap.presentation.theme.MangaStyle

data class CurrentSettings(
    val isDarkMode: Boolean,
    val textSize: MangaSize,
    val paddingSize: MangaSize,
    val cornerStyle: MangaCorners,
    val style: MangaStyle,
)
