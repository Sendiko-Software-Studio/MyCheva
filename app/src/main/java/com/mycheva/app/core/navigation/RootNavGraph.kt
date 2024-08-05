package com.mycheva.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mycheva.app.login.presentation.LoginScreen
import com.mycheva.app.login.presentation.LoginScreenViewModel
import com.mycheva.app.reset_password.presentation.ResetPasswordScreen
import com.mycheva.app.reset_password.presentation.ResetPasswordScreenViewModel
import com.mycheva.app.splashscreen.presentation.SplashScreen
import com.mycheva.app.splashscreen.presentation.SplashScreenViewModel

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AuthGraph
    ) {
        navigation<AuthGraph>(
            startDestination = SplashScreen
        ) {
            composable<SplashScreen> {
                val viewModel = hiltViewModel<SplashScreenViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                SplashScreen(
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
                        navController.navigate(it)
                    }
                )
            }
            composable<ResetPasswordScreen> {
                val viewModel = viewModel<ResetPasswordScreenViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                ResetPasswordScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                )
            }
        }
        composable<MainGraph> {
            MainNavGraphContainer(
                onNavigateOut = {
                    navController.navigate(it) { popUpTo(it) { inclusive  = true } }
                }
            )
        }
    }
}