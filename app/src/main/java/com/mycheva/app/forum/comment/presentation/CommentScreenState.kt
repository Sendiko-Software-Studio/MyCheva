package com.mycheva.app.forum.comment.presentation

import com.mycheva.app.forum.comment.data.Comment
import com.mycheva.app.forum.main.data.Forum

data class CommentScreenState(
    val comments: List<Comment> = emptyList(),
    val post: Forum = Forum(),
    val isError: Boolean = false,
    val notificationMessage: String = "",
    val isLoading: Boolean = false,
    val totalComment: Int = 0,
    val userProfileUrl: String = "",
    val commentText: String = "",
)
