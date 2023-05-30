package com.example.myap.presentation.ui.utils

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.myap.presentation.theme.MangaCorners
import com.example.myap.presentation.theme.MangaSize
import com.example.myap.presentation.theme.MangaStyle
import com.example.myap.presentation.ui.settings.CurrentSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsEventBus {

    private val _currentSettings: MutableStateFlow<CurrentSettings> = MutableStateFlow(
        CurrentSettings(
            isDarkMode = true,
            cornerStyle = MangaCorners.Rounded,
            style = MangaStyle.Orange,
            textSize = MangaSize.Medium,
            paddingSize = MangaSize.Medium
        )
    )

    val currentSettings: StateFlow<CurrentSettings> = _currentSettings

    fun updateDarkMode(isDarkMode: Boolean) {
        _currentSettings.value = _currentSettings.value.copy(isDarkMode = isDarkMode)
    }

    fun updateCornerStyle(corners: MangaCorners) {
        _currentSettings.value = _currentSettings.value.copy(cornerStyle = corners)
    }

    fun updateStyle(style: MangaStyle) {
        _currentSettings.value = _currentSettings.value.copy(style = style)
    }
}
val LocalSettingsEventBus = staticCompositionLocalOf {
    SettingsEventBus()
}
