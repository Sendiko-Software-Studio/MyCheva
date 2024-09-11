package com.mycheva.app.profile.main.presentation

data class ProfileState(
    val id: String = "",
    val imageUrl: String = "",
    val username: String = "",
    val name: String = "",
    val email: String = "",
    val nim: String = "",
    val faculty: String = "",
    val major: String = "",
    val division: String = "",
    val token: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val notificationMessage: String = "",
    val isLogoutSuccess: Boolean = false,
    val isEditingUsername: Boolean = false,
    val isEditUsernameSuccess: Boolean = false,
    val isChangingPassword: Boolean = false,
    val isChangingPasswordSuccess: Boolean = false,
)
