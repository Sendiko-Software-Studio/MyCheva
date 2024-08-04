package com.mycheva.app.reset_password.presentation

data class ResetPasswordScreenState(
    val emailText: String = "",
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = "",
)
