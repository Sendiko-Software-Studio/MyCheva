package com.mycheva.app.core.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.mycheva.app.announcement.presentation.AnnouncementScreen
import com.mycheva.app.announcement.presentation.AnnouncementViewModel
import com.mycheva.app.attendance.presentation.AttendanceScreen
import com.mycheva.app.attendance.presentation.AttendanceScreenViewModel
import com.mycheva.app.bookmark.presentation.BookmarkScreen
import com.mycheva.app.bookmark.presentation.BookmarkScreenViewModel
import com.mycheva.app.dashboard.presentation.DashboardScreen
import com.mycheva.app.dashboard.presentation.DashboardViewModel
import com.mycheva.app.forum.add.presentation.AddPostScreen
import com.mycheva.app.forum.add.presentation.AddPostScreenViewModel
import com.mycheva.app.forum.comment.presentation.CommentScreen
import com.mycheva.app.forum.comment.presentation.CommentViewModel
import com.mycheva.app.forum.main.presentation.ForumScreen
import com.mycheva.app.forum.main.presentation.ForumViewModel
import com.mycheva.app.login.presentation.LoginScreen
import com.mycheva.app.login.presentation.LoginScreenViewModel
import com.mycheva.app.profile.edit_pass.presentation.EditPasswordScreen
import com.mycheva.app.profile.edit_pass.presentation.EditPasswordScreenViewModel
import com.mycheva.app.profile.edit_username.presentation.EditUsernameScreen
import com.mycheva.app.profile.edit_username.presentation.EditUsernameScreenViewModel
import com.mycheva.app.profile.main.presentation.ProfileScreen
import com.mycheva.app.profile.main.presentation.ProfileScreenViewModel
import com.mycheva.app.reset_password.presentation.ResetPasswordScreen
import com.mycheva.app.reset_password.presentation.ResetPasswordScreenViewModel
import com.mycheva.app.roadmap.presentation.RoadmapScreen
import com.mycheva.app.schedule.detail.presentation.DetailScheduleScreen
import com.mycheva.app.schedule.detail.presentation.DetailScheduleViewModel
import com.mycheva.app.schedule.main.presentation.ScheduleScreen
import com.mycheva.app.schedule.main.presentation.ScheduleScreenViewModel
import com.mycheva.app.splashscreen.presentation.SplashScreen
import com.mycheva.app.splashscreen.presentation.SplashScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isVisible =
        navBackStackEntry?.destination?.route?.contains(DashboardScreen.toString()) ?: false || navBackStackEntry?.destination?.route?.contains(
            ScheduleScreen.toString()
        ) ?: false || navBackStackEntry?.destination?.route?.contains(ProfileScreen.toString()) ?: false
    var currentRoute by remember {
        mutableStateOf(DashboardScreen.toString())
    }
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
                    val viewModel = hiltViewModel<SplashScreenViewModel>()
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
                    val viewModel = hiltViewModel<LoginScreenViewModel>()
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
                    val viewModel = hiltViewModel<ResetPasswordScreenViewModel>()
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
                    val viewModel = hiltViewModel<DashboardViewModel>()
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
                    val viewModel = hiltViewModel<ProfileScreenViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    ProfileScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            if (it is SplashScreen) {
                                currentRoute = DashboardScreen.toString()
                                navController.navigate(it) { popUpTo(it) { inclusive = false } }
                            } else {
                                navController.navigate(it)
                            }
                        }
                    )
                }
                composable<ScheduleScreen> {
                    val viewModel = hiltViewModel<ScheduleScreenViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    ScheduleScreen(
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
                    val viewModel = hiltViewModel<DetailScheduleViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    DetailScheduleScreen(
                        eventId = args.eventId,
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigateBack = {
                            navController.navigateUp()
                        }
                    )
                }
                composable<EditUsernameScreen> {
                    val viewModel = hiltViewModel<EditUsernameScreenViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    EditUsernameScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            navController.navigateUp()
                        }
                    )
                }
                composable<EditPasswordScreen> {
                    val viewModel = hiltViewModel<EditPasswordScreenViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    EditPasswordScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            navController.navigateUp()
                        }
                    )
                }
                composable<AttendanceScreen> {
                    val viewModel = hiltViewModel<AttendanceScreenViewModel>()
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
                    val viewModel = hiltViewModel<AnnouncementViewModel>()
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
                    val viewModel = hiltViewModel<ForumViewModel>()
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
                    val args = it.toRoute<PostScreen>()
                    val viewModel = hiltViewModel<AddPostScreenViewModel>()
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
                    val viewModel = hiltViewModel<CommentViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    CommentScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        forumId = args.forumId,
                        onNavigateBack = {
                            navController.navigateUp()
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
                composable<RoadmapScreen> {
                    RoadmapScreen(
                        onNavigateBack = {
                            navController.navigateUp()
                        },
                        animatedContentScope = this
                    )
                }
            }
        }
    }
}