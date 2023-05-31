package com.example.myap.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myap.R

sealed class Screen (
    val route: String,
    @StringRes
    val name: Int,
    val icon: ImageVector
) {
    object Home: Screen(
        route = "home",
        name = R.string.screen_home,
        icon = Icons.Filled.Home
    )
    object Settings: Screen(
        route = "settings",
        name = R.string.screen_settings,
        icon = Icons.Filled.Settings
    )
    object Details: Screen(
        route = "details/{mangaId}",
        name = R.string.screen_details,
        icon = Icons.Filled.List
    )
    object Third: Screen(
        route = "third",
        name = R.string.screen_third,
        icon = Icons.Filled.Favorite
    )
}
