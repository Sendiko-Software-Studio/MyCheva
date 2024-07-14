package com.mycheva.app.core.navigation.bottom_nav

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: Any = "",
    val label: String = "",
    val icon: ImageVector
)
