package com.mycheva.app.profile.edit_pass.presentation

sealed class EditPasswordScreenEvent {
    data class OnOldPassChanged(val value: String): EditPasswordScreenEvent()
    data object OnOldPassCleared: EditPasswordScreenEvent()
    data class OnNewPassChanged(val value: String): EditPasswordScreenEvent()
    data object OnNewPassCleared: EditPasswordScreenEvent()
    data object OnSave: EditPasswordScreenEvent()
}