package com.mycheva.app.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.mycheva.app.core.navigation.RootNavGraph
import com.mycheva.app.core.ui.theme.MyChevaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyChevaTheme(
                darkTheme = false
            ) {
                val navController = rememberNavController()
                RootNavGraph(navController = navController)
            }
        }
    }
}