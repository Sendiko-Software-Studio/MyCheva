package com.mycheva.app.forum.main.presentation

import com.mycheva.app.forum.core.data.ForumsItem

data class ForumScreenState(
    val posts: List<ForumsItem> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
    val token: String = "",
)
