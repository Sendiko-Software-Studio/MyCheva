package com.mycheva.app.reset_password.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.reset_password.data.ResetPasswordRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val repository: ResetPasswordRepositoryImpl,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(ResetPasswordState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResetPasswordState())

    fun onEvent(event: ResetPasswordEvent) {
        when (event) {
            is ResetPasswordEvent.OnEmailTextChange -> changeEmail(event.email)
            is ResetPasswordEvent.OnResetPassword -> resetPassword(event.token, event.email)
            ResetPasswordEvent.OnEmailCleared -> clearEmailText()
            ResetPasswordEvent.ClearState -> clearState()
        }
    }

    private fun clearState() {
        _state.update { ResetPasswordState() }
    }

    private fun resetPassword(token: String, email: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.resetPassword(token, email)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isRequestSuccess = true,
                            isLoading = false,
                            notificationMessage = UiText.DynamicString(result)
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _state.update {
                        it.copy(isRequestFailed = true, notificationMessage = error.asUiText())
                    }
                }
        }
    }

    private fun clearEmailText() {
        _state.update {
            it.copy(emailText = "")
        }
    }

    private fun changeEmail(email: String) {
        _state.update {
            it.copy(emailText = email)
        }
    }

}