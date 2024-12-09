package com.mycheva.app.forum.main.presentation

import com.mycheva.app.forum.core.data.Forum

data class ForumState(
    val forums: List<Forum> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
    val token: String = "",
    val searchText: String = "",
)
