package com.mycheva.app.forum.comment.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.components.TextFieldType
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.forum.comment.presentation.component.CommentCard
import com.mycheva.app.forum.comment.presentation.component.PostCard
import com.mycheva.app.forum.main.presentation.ForumUi
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
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
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Forum", fontFamily = poppinsFamily)
                    },
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = Modifier
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            AnimatedVisibility(
                                visible = state.post != null,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {
                                PostCard(forum = ForumUi.toForumUi(state.post!!))
                            }
                        }
                        item {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                CustomTextField(
                                    modifier = Modifier.weight(1f)
                                        .padding(top = 18.dp),
                                    value = state.commentText,
                                    onValueChange = {
                                        onEvent(CommentEvent.OnCommentTextChange(it))
                                    },
                                    label = "Berikan komentar anda",
                                    onClearClick = { },
                                    type = TextFieldType.White,
                                    leadingIcon = {
                                        Icon(Icons.Default.Textsms, contentDescription = null)
                                    }
                                )
                                IconButton(
                                    onClick = {
                                        onEvent(
                                            CommentEvent.OnPostComment(
                                                state.token,
                                                state.userId,
                                                forumId
                                            )
                                        )
                                    }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Rounded.Send,
                                        contentDescription = "send comment"
                                    )
                                }
                            }
                        }
                        item {
                            Text(
                                text = "Komentar (${state.totalComment})",
                                modifier = Modifier.padding(horizontal = 16.dp),
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppinsFamily,
                                fontSize = 14.sp
                            )
                        }
                        items(state.comments) {
                            CommentCard(
                                comment = CommentUi.toCommentUi(it),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}