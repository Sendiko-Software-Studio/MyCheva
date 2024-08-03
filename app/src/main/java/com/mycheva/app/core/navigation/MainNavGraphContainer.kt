package com.mycheva.app.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mycheva.app.core.navigation.bottom_nav.bottomNavItems
import com.mycheva.app.core.ui.theme.Primary100
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.Primary900
import com.mycheva.app.core.ui.theme.Primary950
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun MainNavGraphContainer(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isVisible =
        navBackStackEntry?.destination?.route?.contains(DashboardScreen.toString()) ?: false || navBackStackEntry?.destination?.route?.contains(
            ScheduleScreen.toString()
        ) ?: false || navBackStackEntry?.destination?.route?.contains(ProfileScreen.toString()) ?: false
    var currentRoute by remember {
        mutableStateOf(DashboardScreen.toString())
    }
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = isVisible,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                NavigationBar(
                    containerColor = Primary500,
                ) {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute.contains(item.route.toString()),
                            onClick = {
                                currentRoute = item.route.toString()
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                unselectedIconColor = Primary900,
                                unselectedTextColor = Primary900,
                                selectedIconColor = Primary50,
                                selectedTextColor = Primary50
                            ),
                            label = {
                                Text(
                                    text = item.label,
                                    fontFamily = poppinsFamily,
                                )
                            }
                        )
                    }
                }
            }
        }
    ) {
        MainGraph(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        )
    }
}