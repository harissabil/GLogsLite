package com.harissabil.glogslite.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val iconPressed: ImageVector,
    val screen: Screen
)
