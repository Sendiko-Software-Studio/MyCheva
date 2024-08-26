package com.mycheva.app.login.presentation

import NOT_FOUND
import SERVER_ERROR
import UNAUTHORIZED
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.ui.data.TextFieldError
import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.domain.LoginRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: LoginRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnUsernameChanged -> onUsernameChanged(event.username)
            is LoginScreenEvent.OnPasswordChanged -> onPasswordChanged(event.password)
            is LoginScreenEvent.OnPasswordVisibilityToggle -> onPasswordVisibilityChanged(event.isVisible)
            LoginScreenEvent.OnLogin -> login()
            LoginScreenEvent.OnUsernameCleared -> onUsernameClear()
            LoginScreenEvent.OnClearState -> clearState()
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                notificationMessage = "",
                isLoading = false,
                isSignInFailed = false,
            )
        }
    }

    private fun login() {
        if (state.value.usernameText.isBlank()) {
            _state.update {
                it.copy(
                    usernameTextState = TextFieldError(
                        isError = true,
                        errorMessage = "Username can't be null."
                    )
                )
            }
            return
        }
        if (state.value.passwordText.isBlank()) {
            _state.update {
                it.copy(
                    passwordTextState = TextFieldError(
                        isError = true,
                        errorMessage = "Password can't be null."
                    )
                )
            }
            return
        }
        _state.update { it.copy(isLoading = true) }
        val data = LoginRequest(
                name = state.value.usernameText,
                password = state.value.passwordText
            )
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(data)
                .onSuccess { result ->
                    repository.saveToken(result.token)
                    repository.saveUserId(result.user.id.toString())
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSignInSuccessful = true,
                            notificationMessage = "Selamat datang, ${result.user.name}"
                        )
                    }
                }
                .onFailure { code ->
                    when (code.message) {
                        UNAUTHORIZED -> _state.update {
                            it.copy(
                                isLoading = false,
                                isSignInFailed = true,
                                notificationMessage = "Password didn't match."
                            )
                        }
                        NOT_FOUND -> _state.update {
                            it.copy(
                                isLoading = false,
                                isSignInFailed = true,
                                notificationMessage = "Account not found"
                            )
                        }
                        SERVER_ERROR -> _state.update {
                            it.copy(
                                isLoading = false,
                                isSignInFailed = true,
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }
        }
    }

    private fun onUsernameClear() {
        _state.update { it.copy(usernameText = "") }
    }

    private fun onPasswordVisibilityChanged(isVisible: Boolean) {
        _state.update { it.copy(isPasswordVisible = isVisible) }
    }

    private fun onPasswordChanged(password: String) {
        _state.update { it.copy(passwordText = password) }
    }

    private fun onUsernameChanged(username: String) {
        _state.update { it.copy(usernameText = username) }
    }
}