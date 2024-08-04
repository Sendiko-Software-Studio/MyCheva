package com.mycheva.app.profile.edit_username

data class EditUsernameScreenState(
    val usernameText: String = "",
    val isLoading: Boolean = false,
    val notificationMessage: String = ""
)
