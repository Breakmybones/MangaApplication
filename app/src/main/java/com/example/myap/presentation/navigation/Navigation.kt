package com.example.myap.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myap.presentation.theme.MangaTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myap.presentation.ui.HomeScreen
import com.example.myap.presentation.ui.MangaDetailScreen
import com.example.myap.presentation.ui.settings.SettingsScreen
import com.example.myap.presentation.ui.ThirdScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Home
) {

    val items = listOf(
        Screen.Home,
        Screen.Settings,
        Screen.Third
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MangaTheme.colors.primaryBackground,
                elevation = 12.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(text = stringResource(screen.name), style = MangaTheme.typography.manga) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = startDestination.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController = navController) }
            composable(Screen.Settings.route) { SettingsScreen() }
            composable( Screen.Third.route ) { ThirdScreen() }
            composable(
                Screen.Details.route,
                arguments = listOf(navArgument("mangaId") { type = NavType.IntType })
            ) { backStackEntry ->
                MangaDetailScreen(mangaId = backStackEntry.arguments?.getInt("mangaId") ?: 0)
            }
        }
    }
}
