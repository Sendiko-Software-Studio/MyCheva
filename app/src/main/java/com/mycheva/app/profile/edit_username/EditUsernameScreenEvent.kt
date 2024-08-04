package com.mycheva.app.profile.edit_username

sealed class EditUsernameScreenEvent {
    data class OnUsernameChanged(val value: String): EditUsernameScreenEvent()
    data object OnUsernameCleared: EditUsernameScreenEvent()
    data object OnSave: EditUsernameScreenEvent()
}
