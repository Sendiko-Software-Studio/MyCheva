package com.mycheva.app.login.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginScreenEvent) {
        when(event) {
            is LoginScreenEvent.OnUsernameChanged -> onUsernameChanged(event.username)
            is LoginScreenEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is LoginScreenEvent.OnPasswordVisibilityToggle -> onPasswordVisibilityChanged(event.isVisible)
        }
    }

    private fun onPasswordVisibilityChanged(isVisible: Boolean) {
        _state.update{ it.copy(isPasswordVisible = isVisible) }
    }

    private fun onPasswordChanged(password: String) {
        _state.update { it.copy(passwordText = password) }
    }

    private fun onUsernameChanged(username: String) {
        _state.update { it.copy(usernameText = username) }
    }
}