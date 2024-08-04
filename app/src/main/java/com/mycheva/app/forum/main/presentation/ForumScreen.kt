package com.mycheva.app.forum.main.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mycheva.app.core.ui.CenteredAppBar
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.forum.main.presentation.component.ForumPostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(
    state: ForumScreenState,
    onEvent: (ForumScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {
    Scaffold(
        topBar = {
            CenteredAppBar(
                title = "Forum",
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigationAction = { onNavigate(null) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = Primary500,
                contentColor = Primary50,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "add post")
            }
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues
            ) {
                items(state.posts) { post ->
                    ForumPostCard(forum = post)
                }
            }
        }
    )
}


@Preview
@Composable
private fun ForumScreenPrev() {
    ForumScreen(
        state = ForumScreenState(),
        onEvent = { },
        onNavigate = { }
    )
}