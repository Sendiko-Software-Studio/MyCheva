package com.mycheva.app.forum.comment.presentation

import com.mycheva.app.forum.core.data.Forum
import com.mycheva.app.forum.core.data.Replies

data class CommentScreenState(
    val comments: List<Replies> = emptyList(),
    val post: Forum? = null,
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
