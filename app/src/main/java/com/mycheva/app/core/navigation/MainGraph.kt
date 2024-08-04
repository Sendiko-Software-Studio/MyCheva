package com.mycheva.app.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mycheva.app.announcement.presentation.AnnouncementScreen
import com.mycheva.app.announcement.presentation.AnnouncementViewModel
import com.mycheva.app.attendance.presentation.AttendanceScreen
import com.mycheva.app.bookmark.presentation.BookmarkScreen
import com.mycheva.app.bookmark.presentation.BookmarkScreenViewModel
import com.mycheva.app.dashboard.presentation.DashboardScreen
import com.mycheva.app.forum.main.presentation.ForumScreen
import com.mycheva.app.forum.main.presentation.ForumScreenViewModel
import com.mycheva.app.profile.edit_pass.EditPasswordScreen
import com.mycheva.app.profile.edit_pass.EditPasswordScreenViewModel
import com.mycheva.app.profile.edit_username.EditUsernameScreen
import com.mycheva.app.profile.edit_username.EditUsernameScreenViewModel
import com.mycheva.app.profile.main.presentation.ProfileScreen
import com.mycheva.app.profile.main.presentation.ProfileScreenViewModel
import com.mycheva.app.schedule.ScheduleScreen

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
        composable<ProfileScreen> {
            val viewModel = viewModel<ProfileScreenViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            ProfileScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onNavigate = {
                    navController.navigate(it)
                }
            )
        }
        composable<ScheduleScreen> {
            ScheduleScreen()
        }
        navigation<DetailsGraph>(
            startDestination = AttendanceScreen
        ) {
            composable<EditUsernameScreen> {
                val viewModel = viewModel<EditUsernameScreenViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                EditUsernameScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { }
                )
            }
            composable<EditPasswordScreen> {
                val viewModel = viewModel<EditPasswordScreenViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                EditPasswordScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { }
                )
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
                val state by viewModel.state.collectAsStateWithLifecycle()
                AnnouncementScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = {
                        if (it == null) {
                            navController.navigateUp()
                        } else navController.navigate(it)
                    }
                )
            }
            composable<ForumScreen> {
                val viewModel = viewModel<ForumScreenViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                ForumScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = {
                        if (it == null) {
                            navController.navigateUp()
                        } else navController.navigate(it)
                    }
                )
            }
            composable<BookmarkScreen> {
                val viewModel = viewModel<BookmarkScreenViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                BookmarkScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = {
                        if (it == null) {
                            navController.navigateUp()
                        } else navController.navigate(it)
                    }
                )
            }
        }
    }
}