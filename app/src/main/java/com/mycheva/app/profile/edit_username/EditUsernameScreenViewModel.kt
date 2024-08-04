package com.mycheva.app.profile.edit_username

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditUsernameScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(EditUsernameScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: EditUsernameScreenEvent) {
        when (event) {
            is EditUsernameScreenEvent.OnUsernameChanged -> onUsernameChanged(event.value)
            EditUsernameScreenEvent.OnUsernameCleared -> onUsernameCleared()
            EditUsernameScreenEvent.OnSave -> TODO()
        }
    }

    private fun onUsernameCleared() {
        _state.update { it.copy(usernameText = "") }
    }

    private fun onUsernameChanged(value: String) {
        _state.update { it.copy(usernameText = value) } }
    }

}