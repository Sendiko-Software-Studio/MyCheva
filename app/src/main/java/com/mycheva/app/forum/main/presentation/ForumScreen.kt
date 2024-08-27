package com.mycheva.app.forum.main.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.navigation.CommentScreen
import com.mycheva.app.core.navigation.PostScreen
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.components.TextFieldType
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.forum.main.presentation.component.ForumPostCard
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ForumScreen(
    state: ForumScreenState,
    onEvent: (ForumScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
    animatedContentScope: AnimatedContentScope
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank()) {
            delay(300)
            onEvent(ForumScreenEvent.OnLoadForums(state.token))
        }
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(ForumScreenEvent.OnClearNotification)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isRequestError
    ) {
        Scaffold(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "forum"),
                    animatedVisibilityScope = animatedContentScope
                ),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Forum",
                            fontFamily = poppinsFamily
                        )
                    },
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(onClick = { onNavigate(null) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNavigate(PostScreen("")) },
                    containerColor = Primary500,
                    contentColor = Primary50,
                    shape = CircleShape
                ) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = "add post")
                }
            },
            content = { paddingValues ->
                val padding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                LazyColumn(
                    contentPadding = padding,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    item {
                        CustomTextField(
                            value = "",
                            onValueChange = {},
                            onClearClick = { /*TODO*/ },
                            type = TextFieldType.White,
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = "Cari..",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "cari"
                                )
                            }
                        )
                    }
                    items(state.posts) { post ->
                        ForumPostCard(
                            forum = post,
                            onNavigate = {
                                onNavigate(CommentScreen(forumId = it))
                            }
                        )
                    }
                }
            }
        )
    }
}
