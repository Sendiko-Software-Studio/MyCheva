package com.mycheva.app.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mycheva.app.core.navigation.bottom_nav.BottomNavbar

@Composable
fun MainNavGraphContainer(
    state: NavigationState,
    onNavigate: (Int, String) -> Unit,
) {
    Scaffold(
        bottomBar = {
            BottomNavbar(
                state = state,
                onClick = { index, route ->
                    onNavigate(index, route)
                }
            )
        }
    ) {
        MainNavGraph(
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding())
                .fillMaxSize()
        )
    }
}