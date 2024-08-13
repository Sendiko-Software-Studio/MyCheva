package com.mycheva.app.forum.comment.presentation

import com.mycheva.app.forum.core.data.ForumsItem
import com.mycheva.app.forum.core.data.RepliesItem

data class CommentScreenState(
    val comments: List<RepliesItem> = emptyList(),
    val post: ForumsItem? = null,
    val isError: Boolean = false,
    val notificationMessage: String = "",
    val isLoading: Boolean = false,
    val totalComment: Int = 0,
    val userProfileUrl: String = "",
    val commentText: String = "",
    val userId: String = "",
    val token: String = "",
    val isCommentPosted: Boolean = false,
)
