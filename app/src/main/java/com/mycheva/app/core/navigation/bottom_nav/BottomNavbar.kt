package com.mycheva.app.core.navigation.bottom_nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mycheva.app.core.navigation.NavigationState
import com.mycheva.app.core.ui.theme.Primary100
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.Primary900
import com.mycheva.app.core.ui.theme.Primary950

@Composable
fun BottomNavbar(
    state: NavigationState,
    onClick: (Int, String) -> Unit
) {
    NavigationBar(
        containerColor = Primary500
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == state.currentScreen,
                onClick = { onClick(index, item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Primary100,
                    unselectedIconColor = Primary900,
                    unselectedTextColor = Primary900,
                    selectedIconColor = Primary950,
                    selectedTextColor = Primary950
                ),
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}