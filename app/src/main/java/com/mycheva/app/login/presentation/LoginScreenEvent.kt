package com.mycheva.app.login.presentation

sealed class LoginScreenEvent {
    data class OnUsernameChanged(val username: String): LoginScreenEvent()
    data class OnPasswordChanged(val password: String): LoginScreenEvent()
    data class OnPasswordVisibilityToggle(val isVisible: Boolean): LoginScreenEvent()
    data object OnLogin: LoginScreenEvent()
}