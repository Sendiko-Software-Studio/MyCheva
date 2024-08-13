package com.mycheva.app.forum.add.presentation

data class AddPostScreenState(
    val isLoading: Boolean = false,
    val isRequestError: Boolean = false,
    val isRequestSuccess: Boolean = false,
    val notificationMessage: String = "",
    val postText: String = "",
    val token: String = "",
    val userId: String = "",
    val userProfileUrl: String = "",
)
