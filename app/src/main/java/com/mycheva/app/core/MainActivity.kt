package com.mycheva.app.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mycheva.app.core.navigation.Screens
import com.mycheva.app.core.navigation.Screens.SplashScreen
import com.mycheva.app.core.ui.theme.MyChevaTheme
import com.mycheva.app.dashboard.presentation.DashboardScreen
import com.mycheva.app.login.presentation.LoginScreen
import com.mycheva.app.login.presentation.LoginScreenViewModel
import com.mycheva.app.splashscreen.presentation.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyChevaTheme(
                darkTheme = false
            ) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = SplashScreen.name){
                    /* Tambahkan Screen Composable disini */
                    composable(
                        route = SplashScreen.name
                    ) {
                        SplashScreen(
                            onNavigate = {
                                navController.navigate(Screens.LoginScreen.name)
                            }
                        )
                    }
                    composable(
                        route = Screens.LoginScreen.name
                    ) {
                        val viewModel = viewModel<LoginScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        LoginScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            onNavigate = { navController.navigate(Screens.DashboardScreen.name) }
                        )
                    }
                    composable(
                        route = Screens.DashboardScreen.name
                    ) {
                        DashboardScreen()
                    }
                }
            }
        }
    }
}