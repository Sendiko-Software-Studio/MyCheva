package com.mycheva.app.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mycheva.app.announcement.AnnouncementScreen
import com.mycheva.app.attendance.presentation.AttendanceScreen
import com.mycheva.app.bookmark.BookmarkScreen
import com.mycheva.app.dashboard.presentation.DashboardScreen
import com.mycheva.app.profile.presentation.ProfileScreen

@Composable
fun MainGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = DashboardScreen,
        modifier = modifier.fillMaxSize()
    ) {
        composable<DashboardScreen> {
            DashboardScreen(
                onNavigate = {
                    navController.navigate(it)
                }
            )
        }
        navigation<DetailsGraph>(
            startDestination = ProfileScreen
        ) {
            composable<ProfileScreen> {
                ProfileScreen()
            }
            composable<AttendanceScreen> {
                AttendanceScreen(
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable<AnnouncementScreen> {
                AnnouncementScreen()
            }
            composable<BookmarkScreen> {
                BookmarkScreen()
            }
        }
    }
}