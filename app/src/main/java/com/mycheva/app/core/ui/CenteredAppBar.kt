package com.mycheva.app.core.ui

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.mycheva.app.core.navigation.BookmarkScreen
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector,
    navigationAction: (any: Any?) -> Unit,
    actionIcon: ImageVector? = null,
    actionAction: (any: Any?) -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Primary500,
            navigationIconContentColor = Primary50,
            actionIconContentColor = Primary50,
            titleContentColor = Primary50
        ),
        navigationIcon = {
            IconButton(onClick = { navigationAction(null) }) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = "arrow back"
                )
            }
        },
        actions = {
            IconButton(onClick = { actionAction(BookmarkScreen) }) {
                actionIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "bookmark"
                    )
                }
            }
        }
    )
}