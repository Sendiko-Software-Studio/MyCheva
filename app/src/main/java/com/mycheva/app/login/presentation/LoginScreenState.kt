package com.mycheva.app.login.presentation

import com.mycheva.app.core.ui.data.TextFieldError

data class LoginScreenState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val isSignInFailed: Boolean = false,
    val notificationMessage: String = "",
    val usernameText: String = "",
    val usernameTextState: TextFieldError = TextFieldError(),
    val passwordText: String = "",
    val passwordTextState: TextFieldError = TextFieldError(),
    val isPasswordVisible: Boolean = false,
)
