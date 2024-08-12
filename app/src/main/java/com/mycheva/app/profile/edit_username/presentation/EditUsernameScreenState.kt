package com.mycheva.app.profile.edit_username.presentation

data class EditUsernameScreenState(
    val usernameText: String = "",
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestFailed: Boolean = false,
    val isRequestSuccess: Boolean = false,
    val token: String = "",
    val userId: String = ""
)
