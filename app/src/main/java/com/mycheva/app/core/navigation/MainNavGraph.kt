package com.mycheva.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mycheva.app.dashboard.presentation.DashboardScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.DashboardScreen.name,
        modifier = modifier
    ) {
        composable(
            route = Screens.DashboardScreen.name
        ) {
            DashboardScreen()
        }
    }
}