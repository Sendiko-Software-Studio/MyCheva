package com.mycheva.app.core.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.mycheva.app.core.navigation.BookmarkScreen
import com.mycheva.app.core.ui.theme.Neutral200
import com.mycheva.app.core.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector,
    navigationAction: (any: Any?) -> Unit,
    actionIcon: ImageVector? = null,
    actionAction: (any: Any?) -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) {
    MediumTopAppBar(
        modifier = modifier,
        title = { Text(text = title, fontFamily = poppinsFamily) },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            scrolledContainerColor = Neutral200
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
        },
    )
}