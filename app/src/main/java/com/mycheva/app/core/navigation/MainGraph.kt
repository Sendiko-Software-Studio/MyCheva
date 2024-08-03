package com.mycheva.app.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mycheva.app.announcement.AnnouncementScreen
import com.mycheva.app.announcement.AnnouncementViewModel
import com.mycheva.app.attendance.presentation.AttendanceScreen
import com.mycheva.app.bookmark.BookmarkScreen
import com.mycheva.app.bookmark.BookmarkScreenViewModel
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
                val viewModel = viewModel<AnnouncementViewModel>()
                val state by viewModel.state.collectAsState()
                AnnouncementScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
            composable<BookmarkScreen> {
                val viewModel = viewModel<BookmarkScreenViewModel>()
                val state by viewModel.state.collectAsState()
                BookmarkScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}