package com.example.myap.presentation.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myap.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.myap.presentation.theme.*
import com.example.myap.presentation.theme.*
import com.example.myap.presentation.ui.utils.Click


@Composable
fun SettingsScreen(
) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    Surface(color = MangaTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = MangaTheme.colors.primaryBackground,
                elevation = 8.dp
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = MangaTheme.shapes.padding),
                    text = stringResource(id = R.string.screen_settings),
                    color = MangaTheme.colors.primaryText,
                    style = MangaTheme.typography.toolbar
                )
            }

            Row(
                modifier = Modifier.padding(MangaTheme.shapes.padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Dark Theme",
                    color = MangaTheme.colors.primaryText,
                    style = MangaTheme.typography.body
                )
                Checkbox(
                    checked = currentSettings.isDarkMode, onCheckedChange = {
                        settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MangaTheme.colors.tintColor,
                        uncheckedColor = MangaTheme.colors.secondaryText
                    )
                )
            }
            Divider(
                modifier = Modifier.padding(start = MangaTheme.shapes.padding),
                thickness = 0.5.dp,
                color = MangaTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = MangaTheme.colors.secondaryBackground,
                shape = MangaTheme.shapes.cornersStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Shape type", color = MangaTheme.colors.secondaryText)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Card(
                            modifier = Modifier.weight(1f),
                            backgroundColor = MangaTheme.colors.primaryBackground
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp),
                                    text = "Round",
                                    color = MangaTheme.colors.primaryText,
                                    style = MangaTheme.typography.body
                                )
                                Checkbox(
                                    checked = currentSettings.cornerStyle == MangaCorners.Rounded, onCheckedChange = {
                                        settingsEventBus.updateCornerStyle(MangaCorners.Rounded)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = MangaTheme.colors.tintColor,
                                        uncheckedColor = MangaTheme.colors.secondaryText
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Card(
                            modifier = Modifier.weight(1f),
                            backgroundColor = MangaTheme.colors.primaryBackground
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp),
                                    text = "Flat",
                                    color = MangaTheme.colors.primaryText,
                                    style = MangaTheme.typography.body
                                )
                                Checkbox(
                                    checked = currentSettings.cornerStyle == MangaCorners.Flat, onCheckedChange = {
                                        settingsEventBus.updateCornerStyle(MangaCorners.Flat)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = MangaTheme.colors.tintColor,
                                        uncheckedColor = MangaTheme.colors.secondaryText
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Divider(
                modifier = Modifier.padding(start = MangaTheme.shapes.padding),
                thickness = 0.5.dp,
                color = MangaTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = MangaTheme.colors.secondaryBackground,
                shape = MangaTheme.shapes.cornersStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tint color", color = MangaTheme.colors.secondaryText)

                    Row(
                        modifier = Modifier
                            .padding(MangaTheme.shapes.padding)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ColorCard(color = if (currentSettings.isDarkMode) purpleDarkPalette.tintColor
                        else purpleLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MangaStyle.Purple)
                            })
                        ColorCard(color = if (currentSettings.isDarkMode) orangeDarkPalette.tintColor
                        else orangeLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MangaStyle.Orange)
                            })
                        ColorCard(color = if (currentSettings.isDarkMode) blueDarkPalette.tintColor
                        else blueLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(MangaStyle.Blue)
                            })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColorCard(color: Color, onClick: Click) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .size(56.dp, 56.dp),
        backgroundColor = color,
        shape = MangaTheme.shapes.cornersStyle,
        elevation = 3.dp,
    ) {}
}
