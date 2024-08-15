package com.mycheva.app.forum.comment.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mycheva.app.core.ui.components.CenteredAppBar
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.components.PlainTextField
import com.mycheva.app.forum.comment.presentation.component.CommentCard
import com.mycheva.app.forum.main.presentation.component.ForumPostCard
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(
    state: CommentScreenState,
    onEvent: (CommentEvent) -> Unit,
    onNavigateBack: () -> Unit,
    forumId: String
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank()) {
            onEvent(CommentEvent.OnLoadData(state.token, forumId))
        }
    }

    LaunchedEffect(key1 = state.isCommentPosted) {
        if (state.isCommentPosted) {
            onEvent(CommentEvent.OnLoadData(state.token, forumId))
        }
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(CommentEvent.OnClearState)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isError
    ) {
        Scaffold(
            topBar = {
                CenteredAppBar(
                    title = "Forum",
                    navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
                    navigationAction = { onNavigateBack() },
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    AsyncImage(
                        model = state.userProfileUrl,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                    PlainTextField(
                        modifier = Modifier.weight(1f),
                        value = state.commentText,
                        onValueChange = {
                            onEvent(CommentEvent.OnCommentTextChange(it))
                        },
                        placeholder = "Berikan komentar anda"
                    )
                    IconButton(onClick = { onEvent(CommentEvent.OnPostComment(state.token, state.userId, forumId)) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.Send,
                            contentDescription = "send comment"
                        )
                    }
                }
            },
            content = { paddingValues ->
                LazyColumn(
                    contentPadding = paddingValues,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
                        AnimatedVisibility(
                            visible = state.post != null,
                            enter = slideInVertically(),
                            exit = slideOutHorizontally()
                        ) {
                            ForumPostCard(
                                forum = state.post!!, onNavigate = {},
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                    item {
                        Text(
                            text = "Komentar (${state.totalComment})",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    items(state.comments) {
                        CommentCard(
                            comment = it,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        )
    }
}