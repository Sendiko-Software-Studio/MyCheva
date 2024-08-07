package com.mycheva.app.splashscreen.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mycheva.app.R
import com.mycheva.app.core.navigation.LoginScreen
import com.mycheva.app.core.navigation.MainGraph
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SharedTransitionScope.SplashScreen(
    modifier: Modifier = Modifier,
    state: SplashScreenState,
    onNavigate: (destination: Any) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank()) {
            delay(1000)
            onNavigate(MainGraph)
        } else {
            delay(1000)
            onNavigate(LoginScreen)
        }
    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier
                    .size(128.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "chevalier_logo"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                painter = painterResource(id = R.drawable.chevalier_logo),
                contentDescription = "Chevalier Lab"
            )
        }
    }
}