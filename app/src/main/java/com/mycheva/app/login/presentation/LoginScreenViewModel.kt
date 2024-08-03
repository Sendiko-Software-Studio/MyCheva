package com.mycheva.app.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.login.domain.LoginUseCase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginScreenEvent) {
        when(event) {
            is LoginScreenEvent.OnUsernameChanged -> onUsernameChanged(event.username)
            is LoginScreenEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is LoginScreenEvent.OnPasswordVisibilityToggle -> onPasswordVisibilityChanged(event.isVisible)
            LoginScreenEvent.OnLogin -> {
                val verifyForm = loginUseCase.verifyForm(
                    username = state.value.usernameText,
                    password = state.value.passwordText
                )
                if (verifyForm) {
                    viewModelScope.launch {
                        loginUseCase(
                            username = state.value.usernameText,
                            password = state.value.passwordText
                        )
                        _state.update { it.copy(isSignInSuccessful = true) }
                    }
                }
            }

            LoginScreenEvent.OnUsernameCleared -> onUsernameClear()
        }
    }

    private fun onUsernameClear() {
        _state.update { it.copy(usernameText = "") }
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