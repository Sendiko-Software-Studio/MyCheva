package com.mycheva.app.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mycheva.app.core.navigation.Screens.SplashScreen
import com.mycheva.app.core.ui.theme.MyChevaTheme
import com.mycheva.app.splashscreen.presentation.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyChevaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = SplashScreen.name){
                    /* Tambahkan Screen Composable disini */
                    composable(
                        route = SplashScreen.name
                    ) {
                        SplashScreen(
                            onNavigate = { }
                        )
                    }
                }
            }
        }
    }
}