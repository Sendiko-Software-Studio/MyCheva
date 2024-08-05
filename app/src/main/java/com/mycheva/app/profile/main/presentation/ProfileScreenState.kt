package com.mycheva.app.profile.main.presentation

import com.mycheva.app.profile.main.data.User

data class ProfileScreenState(
    val user: User = User(),
    val token: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val notificationMessage: String = ""
)
