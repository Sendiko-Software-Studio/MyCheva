package com.mycheva.app.profile.edit_username.presentation

sealed class EditUsernameScreenEvent {
    data class OnUsernameChanged(val value: String): EditUsernameScreenEvent()
    data object OnUsernameCleared: EditUsernameScreenEvent()
    data class OnSave(val token: String, val userId: String, val name: String): EditUsernameScreenEvent()
    data object OnClearState: EditUsernameScreenEvent()
}
