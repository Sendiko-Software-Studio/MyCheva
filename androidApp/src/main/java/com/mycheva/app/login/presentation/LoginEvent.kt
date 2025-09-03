package com.mycheva.app.login.presentation

sealed class LoginEvent {
    data class OnUsernameChanged(val username: String): LoginEvent()
    data object OnUsernameCleared: LoginEvent()
    data class OnPasswordChanged(val password: String): LoginEvent()
    data class OnPasswordVisibilityToggle(val isVisible: Boolean): LoginEvent()
    data object OnLogin: LoginEvent()
    data object OnClearState: LoginEvent()
}