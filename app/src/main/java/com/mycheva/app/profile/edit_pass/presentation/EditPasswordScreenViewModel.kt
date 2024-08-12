package com.mycheva.app.profile.edit_pass.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditPasswordScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(EditPasswordScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: EditPasswordScreenEvent) {
        when (event) {
            is EditPasswordScreenEvent.OnNewPassChanged -> onNewPassChanged(event.value)
            is EditPasswordScreenEvent.OnOldPassChanged -> onOldPassChanged(event.value)
            EditPasswordScreenEvent.OnNewPassCleared -> onNewPassCleared()
            EditPasswordScreenEvent.OnOldPassCleared -> onOldPassCleared()
            EditPasswordScreenEvent.OnSave -> TODO()
        }
    }

    private fun onOldPassCleared() {
        _state.update {
            it.copy(oldPassText = "")
        }
    }

    private fun onNewPassCleared() {
        _state.update {
            it.copy(newPassText = "")
        }
    }

    private fun onNewPassChanged(value: String) {
        _state.update {
            it.copy(newPassText = value)
        }
    }

    private fun onOldPassChanged(value: String) {
        _state.update {
            it.copy(oldPassText = value)
        }
    }

}