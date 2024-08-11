package com.mycheva.app.profile.main.presentation

data class ProfileScreenState(
    val id: String = "",
    val imageUrl: String = "",
    val username: String = "",
    val name: String = "",
    val nim: String = "",
    val faculty: String = "",
    val major: String = "",
    val division: String = "",
    val token: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val notificationMessage: String = "",
    val isLogoutSuccess: Boolean = false,
)
