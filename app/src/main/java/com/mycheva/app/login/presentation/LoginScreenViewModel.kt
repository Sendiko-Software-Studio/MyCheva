package com.mycheva.app.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.ui.data.TextFieldError
import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse
import com.mycheva.app.login.domain.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: LoginRepository
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
        val data =
            LoginRequest(
                name = state.value.usernameText,
                password = state.value.passwordText
            )
        val request = repository.login(data)
        request.enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    Log.i("DEBUG", "loginRepository, onResponse: run")
                    when (response.code()) {
                        200 -> {
                            viewModelScope.launch {
                                repository.saveToken(response.body()!!.token)
                                repository.saveUserId(response.body()!!.user.id.toString())
                            }
                            _state.update { it.copy(isSignInSuccessful = true) }
                        }

                        401 -> _state.update {
                            it.copy(
                                isSignInFailed = true,
                                notificationMessage = "Username atau password salah.",
                            )
                        }

                        else -> _state.update {
                            it.copy(
                                isSignInFailed = true,
                                notificationMessage = "Server error.",
                            )
                        }
                    }
                }

                override fun onFailure(
                    call: Call<LoginResponse>,
                    throwable: Throwable
                ) {
                    _state.update {
                        it.copy(
                            isSignInFailed = true,
                            notificationMessage = "Server error.",
                        )
                    }
                }

            }
        )
        Log.i("DEBUG", "viewModel, isLoading: ${state.value.isLoading}")
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