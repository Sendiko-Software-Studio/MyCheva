package com.mycheva.app.login.presentation

import com.mycheva.app.core.ui.data.TextFieldError
import com.mycheva.app.core.ui.utils.UiText

data class LoginState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val isSignInFailed: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val usernameText: String = "",
    val usernameTextState: TextFieldError = TextFieldError(),
    val passwordText: String = "",
    val passwordTextState: TextFieldError = TextFieldError(),
    val isPasswordVisible: Boolean = false,
)
