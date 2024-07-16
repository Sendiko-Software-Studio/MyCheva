package com.mycheva.app.login.presentation

data class LoginScreenState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val isSignInFailed: Boolean = false,
    val notificationMessage: String = "",
    val usernameText: String = "",
    val passwordText: String = "",
    val isPasswordVisible: Boolean = false,
)
