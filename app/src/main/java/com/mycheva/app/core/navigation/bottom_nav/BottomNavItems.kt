package com.mycheva.app.core.navigation.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Forum
import com.mycheva.app.core.navigation.Screens

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Dashboard",
        route = Screens.DashboardScreen.name,
        icon = Icons.Rounded.Dashboard,
    ),
    BottomNavItem(
        label = "Jadwal",
        route = "",
        icon = Icons.Rounded.CalendarMonth,
    ),
    BottomNavItem(
        label = "Forum",
        route = "",
        icon = Icons.Rounded.Forum,
    ),
)