package com.mycheva.app.forum.add.presentation

import com.mycheva.app.core.ui.utils.UiText

data class AddPostForumState(
    val isLoading: Boolean = false,
    val isRequestError: Boolean = false,
    val isRequestSuccess: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val postText: String = "",
    val token: String = "",
    val userId: String = "",
    val userProfileUrl: String = "",
)
