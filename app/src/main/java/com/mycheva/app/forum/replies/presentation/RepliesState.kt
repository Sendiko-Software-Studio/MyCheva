package com.mycheva.app.forum.replies.presentation

import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.forum.core.domain.Forum
import com.mycheva.app.forum.replies.domain.Replies

data class RepliesState(
    val replies: List<Replies> = emptyList(),
    val post: Forum? = null,
    val isError: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val isLoading: Boolean = false,
    val totalComment: Int = 0,
    val userProfileUrl: String = "",
    val commentText: String = "",
    val userId: String = "",
    val token: String = "",
    val isReplyPosted: Boolean = false,
)
