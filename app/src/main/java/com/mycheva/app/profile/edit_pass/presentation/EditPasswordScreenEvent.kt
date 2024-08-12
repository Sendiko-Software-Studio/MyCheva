package com.mycheva.app.profile.edit_pass.presentation

sealed class EditPasswordScreenEvent {
    data class OnOldPassChanged(val value: String): EditPasswordScreenEvent()
    data object OnOldPassCleared: EditPasswordScreenEvent()
    data class OnNewPassChanged(val value: String): EditPasswordScreenEvent()
    data object OnNewPassCleared: EditPasswordScreenEvent()
    data class OnSave(val token: String, val userId: String, val newPass: String): EditPasswordScreenEvent()
    data object OnClearState: EditPasswordScreenEvent()
}