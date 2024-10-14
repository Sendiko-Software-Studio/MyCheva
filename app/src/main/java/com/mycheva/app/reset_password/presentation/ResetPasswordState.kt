package com.mycheva.app.reset_password.presentation

data class ResetPasswordState(
    val emailText: String = "",
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = "",
    val token: String = "",
    val isRequestSuccess: Boolean = false,
    val userId: String = "",
)
