package com.mycheva.app.forum.main.presentation

import com.mycheva.app.forum.main.data.Forum

data class ForumScreenState(
    val posts: List<Forum> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
)
