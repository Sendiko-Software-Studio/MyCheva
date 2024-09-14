package com.mycheva.app.profile.main.presentation

sealed class ProfileEvent {
    data class OnLogout(val token: String): ProfileEvent()
    data object OnClearState: ProfileEvent()
    data object OnClearMessage: ProfileEvent()
    data class OnGetProfile(val token: String, val userId: String): ProfileEvent()
    data class OnUsernameEdit(val username: String): ProfileEvent()
    data class OnPasswordEdit(val password: String, val oldPassword: String): ProfileEvent()
    data class OnUsernameSheetToggle(val isVisible: Boolean): ProfileEvent()
    data class OnPasswordSheetToggle(val isVisible: Boolean): ProfileEvent()
}