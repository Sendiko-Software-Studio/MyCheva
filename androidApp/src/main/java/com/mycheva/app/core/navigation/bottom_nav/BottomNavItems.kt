package com.mycheva.app.core.navigation.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Person
import com.mycheva.app.core.navigation.DashboardScreen
import com.mycheva.app.core.navigation.ProfileScreen
import com.mycheva.app.core.navigation.ScheduleScreen

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Dashboard",
        route = DashboardScreen,
        icon = Icons.Rounded.Dashboard,
    ),
    BottomNavItem(
        label = "Jadwal",
        route = ScheduleScreen,
        icon = Icons.Rounded.CalendarMonth,
    ),
    BottomNavItem(
        label = "Profile",
        route = ProfileScreen,
        icon = Icons.Rounded.Person,
    ),
)