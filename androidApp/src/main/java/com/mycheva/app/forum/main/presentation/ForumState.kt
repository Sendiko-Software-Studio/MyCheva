package com.mycheva.app.forum.main.presentation

import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.forum.core.domain.Forum

data class ForumState(
    val forums: List<Forum> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val isRequestError: Boolean = false,
    val token: String = "",
    val searchText: String = "",
)
