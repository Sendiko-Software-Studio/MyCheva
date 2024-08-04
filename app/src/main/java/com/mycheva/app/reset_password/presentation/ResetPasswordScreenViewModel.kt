package com.mycheva.app.reset_password.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ResetPasswordScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(ResetPasswordScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: ResetPasswordScreenEvent) {
        when (event) {
            is ResetPasswordScreenEvent.OnEmailTextChange -> onEmailChanged(event)
            ResetPasswordScreenEvent.OnEmailCleared -> onEmailCleared()
            ResetPasswordScreenEvent.OnResetPassword -> TODO()
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