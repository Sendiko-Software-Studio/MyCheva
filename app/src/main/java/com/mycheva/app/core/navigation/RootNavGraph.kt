package com.mycheva.app.core.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.mycheva.app.announcement.presentation.AnnouncementScreen
import com.mycheva.app.announcement.presentation.AnnouncementViewModel
import com.mycheva.app.attendance.presentation.AttendanceScreen
import com.mycheva.app.attendance.presentation.AttendanceViewModel
import com.mycheva.app.bookmark.presentation.BookmarkScreen
import com.mycheva.app.bookmark.presentation.BookmarkViewModel
import com.mycheva.app.dashboard.presentation.DashboardScreen
import com.mycheva.app.dashboard.presentation.DashboardViewModel
import com.mycheva.app.forum.add.presentation.AddPostForumViewModel
import com.mycheva.app.forum.add.presentation.AddPostScreen
import com.mycheva.app.forum.main.presentation.ForumScreen
import com.mycheva.app.forum.main.presentation.ForumViewModel
import com.mycheva.app.forum.replies.presentation.RepliesScreen
import com.mycheva.app.forum.replies.presentation.RepliesViewModel
import com.mycheva.app.login.presentation.LoginScreen
import com.mycheva.app.login.presentation.LoginViewModel
import com.mycheva.app.meeting.detail.presentation.DetailMeetingScreen
import com.mycheva.app.meeting.detail.presentation.DetailMeetingViewModel
import com.mycheva.app.meeting.main.presentation.MeetingsScreen
import com.mycheva.app.meeting.main.presentation.MeetingsViewModel
import com.mycheva.app.profile.main.presentation.ProfileScreen
import com.mycheva.app.profile.main.presentation.ProfileViewModel
import com.mycheva.app.reset_password.presentation.ResetPasswordScreen
import com.mycheva.app.reset_password.presentation.ResetPasswordViewModel
import com.mycheva.app.roadmap.presentation.RoadMapScreen
import com.mycheva.app.roadmap.presentation.RoadMapViewModel
import com.mycheva.app.splashscreen.presentation.SplashScreen
import com.mycheva.app.splashscreen.presentation.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootNavGraph(
    navController: NavHostController,
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = AuthGraph,
            modifier = Modifier
                .padding()
                .fillMaxSize(),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                )
            },
            exitTransition = {
                fadeOut()
            }
        ) {
            navigation<AuthGraph>(
                startDestination = SplashScreen
            ) {
                composable<SplashScreen> {
                    val viewModel = koinViewModel<SplashScreenViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    SplashScreen(
                        animatedVisibilityScope = this,
                        state = state,
                        onNavigate = {
                            navController.navigate(it)
                        }
                    )
                }
                composable<LoginScreen> {
                    val viewModel = koinViewModel<LoginViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    LoginScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            navController.navigate(it) { popUpTo(it) { inclusive = true } }
                        }
                    )
                }
                composable<ResetPasswordScreen> {
                    val viewModel = koinViewModel<ResetPasswordViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    ResetPasswordScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigateBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
            navigation<MainGraph>(
                startDestination = DashboardScreen,
            ) {
                composable<DashboardScreen> {
                    val viewModel = koinViewModel<DashboardViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    DashboardScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            navController.navigate(it)
                        },
                        animatedContentScope = this
                    )
                }
                composable<ProfileScreen> {
                    val viewModel = koinViewModel<ProfileViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    ProfileScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            if (it is SplashScreen) {
                                navController.navigate(it) { popUpTo(it) { inclusive = false } }
                            } else {
                                navController.navigate(it)
                            }
                        }
                    )
                }
                composable<ScheduleScreen> {
                    val viewModel = koinViewModel<MeetingsViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    MeetingsScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        animatedVisibilityScope = this,
                        onNavigate = {
                            if (it.isNotBlank()) {
                                navController.navigate(DetailSchedule(it))
                            } else navController.navigateUp()
                        }
                    )
                }
                composable<DetailSchedule> {
                    val args = it.toRoute<DetailSchedule>()
                    val viewModel = koinViewModel<DetailMeetingViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    DetailMeetingScreen(
                        meetingId = args.eventId,
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigateBack = {
                            navController.navigateUp()
                        }
                    )
                }
                composable<AttendanceScreen> {
                    val viewModel = koinViewModel<AttendanceViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    AttendanceScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigateBack = {
                            navController.navigateUp()
                        },
                        animatedContentScope = this
                    )
                }
                composable<AnnouncementScreen> {
                    val viewModel = koinViewModel<AnnouncementViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    AnnouncementScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            if (it == null) {
                                navController.navigateUp()
                            } else navController.navigate(it)
                        },
                        animatedContentScope = this
                    )
                }
                composable<ForumScreen> {
                    val viewModel = koinViewModel<ForumViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    ForumScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            when (it) {
                                is CommentScreen -> navController.navigate(CommentScreen(forumId = it.forumId))
                                is PostScreen -> navController.navigate(PostScreen(imageUrl = it.imageUrl))
                                null -> navController.navigateUp()
                                else -> navController.navigate(it)
                            }
                        },
                        animatedContentScope = this
                    )
                }
                composable<PostScreen> {
                    val viewModel = koinViewModel<AddPostForumViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    AddPostScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigateBack = {
                            navController.navigateUp()
                        }
                    )
                }
                composable<CommentScreen> {
                    val args = it.toRoute<CommentScreen>()
                    val viewModel = koinViewModel<RepliesViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    RepliesScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        forumId = args.forumId,
                        onNavigateBack = {
                            navController.navigateUp()
                        }
                    )
                }
                composable<BookmarkScreen> {
                    val viewModel = koinViewModel<BookmarkViewModel>()
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
                composable<RoadmapScreen> {
                    val args = it.toRoute<RoadmapScreen>()
                    val viewModel = koinViewModel<RoadMapViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    RoadMapScreen(
                        divisionId = args.divisionId,
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigateBack = {
                            navController.navigateUp()
                        },
                        animationVisibility = this
                    )
                }
            }
        }
    }
}