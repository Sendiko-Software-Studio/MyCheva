package com.mycheva.app.forum.main.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mycheva.app.R
import com.mycheva.app.core.navigation.CommentScreen
import com.mycheva.app.core.navigation.PostScreen
import com.mycheva.app.core.ui.components.CardSkeleton
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.components.TextFieldType
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.forum.main.presentation.component.ForumPostCard
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ForumScreen(
    state: ForumState,
    onEvent: (ForumEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
    animatedContentScope: AnimatedContentScope
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank()) {
            delay(300)
            onEvent(ForumEvent.OnLoadForums(state.token))
        }
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(ForumEvent.OnClearNotification)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = false,
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
                    top = paddingValues.calculateTopPadding()
                )
                LazyColumn(
                    contentPadding = padding,
                    modifier = Modifier
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .fillMaxSize()
                ) {
                    item {
                        CustomTextField(
                            value = state.searchText,
                            onValueChange = { onEvent(ForumEvent.OnSearchTextChange(it)) },
                            onClearClick = { onEvent(ForumEvent.OnClearFilter) },
                            type = TextFieldType.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            label = "Cari..",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "cari"
                                )
                            }
                        )
                    }
                    item {
                        AnimatedVisibility(!state.isLoading && state.posts.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    modifier = Modifier.size(256.dp),
                                    painter = painterResource(R.drawable.not_found),
                                    contentDescription = "Konten tidak ditemukan."
                                )
                            }
                        }
                    }
                    item {
                        (1..5).forEach {
                            AnimatedVisibility(
                                visible = state.isLoading && state.posts.isEmpty(),
                                enter = expandVertically(),
                                exit = shrinkVertically()
                            ) {
                                CardSkeleton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }
                    }
                    items(state.posts) { post ->
                        if (state.posts.first() != post) {
                            HorizontalDivider(modifier = Modifier.fillMaxWidth())
                        }
                        ForumPostCard(
                            forum = ForumUi.toForumUi(post),
                            modifier = Modifier.padding(bottom = 16.dp),
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
