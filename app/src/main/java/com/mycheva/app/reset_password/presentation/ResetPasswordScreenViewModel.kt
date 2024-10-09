package com.mycheva.app.reset_password.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.NOT_FOUND
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.reset_password.data.ResetPasswordRequest
import com.mycheva.app.reset_password.domain.ResetPasswordRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordScreenViewModel @Inject constructor(
    private val repository: ResetPasswordRepositoryImpl
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(ResetPasswordScreenState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResetPasswordScreenState())

    fun onEvent(event: ResetPasswordScreenEvent) {
        when (event) {
            is ResetPasswordScreenEvent.OnEmailTextChange -> onEmailChanged(event)
            is ResetPasswordScreenEvent.OnResetPassword -> resetPassword(event.token, event.email)
            ResetPasswordScreenEvent.OnEmailCleared -> onEmailCleared()
            ResetPasswordScreenEvent.ClearState -> _state.update { ResetPasswordScreenState() }
        }
    }

    private fun resetPassword(token: String, email: String) {
        _state.update { it.copy(isLoading = true) }
        val data = ResetPasswordRequest(email = email)
        viewModelScope.launch(Dispatchers.IO) {
            repository.resetPassword(token, data)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isRequestSuccess = true,
                            isLoading = false,
                            notificationMessage = result.message
                        )
                    }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false) }
                    when (error.message) {
                        NOT_FOUND -> _state.update {
                            it.copy(isRequestFailed = true, notificationMessage = "User not found.")
                        }
                        SERVER_ERROR -> _state.update {
                            it.copy(isRequestFailed = true, notificationMessage = "Server error.")
                        }
                    }
                }
        }
    }

    private fun onEmailCleared() {
        _state.update {
            it.copy(emailText = "")
        }
    }

    private fun onEmailChanged(event: ResetPasswordScreenEvent.OnEmailTextChange) {
        _state.update {
            it.copy(emailText = event.email)
        }
    }

}