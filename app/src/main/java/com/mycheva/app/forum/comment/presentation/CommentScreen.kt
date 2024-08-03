package com.mycheva.app.forum.comment.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mycheva.app.core.ui.components.PlainTextField
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.forum.comment.presentation.component.CommentCard
import com.mycheva.app.forum.main.presentation.component.ForumPostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(
    state: CommentScreenState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Forum")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary500,
                    titleContentColor = Primary50,
                    navigationIconContentColor = Primary50,
                )
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
                    onValueChange = {},
                    placeholder = "Berikan komentar anda"
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.AutoMirrored.Rounded.Send, contentDescription = "send comment")
                }
            }
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues
            ) {
                item {
                    ForumPostCard(forum = state.post)
                }
                item {
                    Text(
                        text = "Komentar (${state.totalComment})",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontWeight = FontWeight.Bold,
                    )
                }
                items(state.comments) {
                    CommentCard(comment = it)
                }
            }
        }
    )
}


@Preview
@Composable
private fun AddCommmentScreenPrev() {
    CommentScreen(
        state = CommentScreenState()
    )
}