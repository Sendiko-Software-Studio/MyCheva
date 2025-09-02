package com.mycheva.app.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.NOT_FOUND
import com.mycheva.app.core.network.utils.SERVER_ERROR
import com.mycheva.app.core.network.utils.UNAUTHORIZED
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.data.TextFieldError
import com.mycheva.app.login.data.dto.LoginRequest
import com.mycheva.app.login.data.LoginRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnUsernameChanged -> changeUsernameText(event.username)
            is LoginEvent.OnPasswordChanged -> changePasswordText(event.password)
            is LoginEvent.OnPasswordVisibilityToggle -> changePasswordVisibility(event.isVisible)
            LoginEvent.OnLogin -> login()
            LoginEvent.OnUsernameCleared -> clearUsername()
            LoginEvent.OnClearState -> clearState()
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
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(state.value.usernameText, state.value.passwordText)
                .onSuccess { result ->
                    repository.saveToken(result)
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

    private fun clearUsername() {
        _state.update { it.copy(usernameText = "") }
    }

    private fun changePasswordVisibility(isVisible: Boolean) {
        _state.update { it.copy(isPasswordVisible = isVisible) }
    }

    private fun changePasswordText(password: String) {
        _state.update { it.copy(passwordText = password) }
    }

    private fun changeUsernameText(username: String) {
        _state.update { it.copy(usernameText = username) }
    }
}