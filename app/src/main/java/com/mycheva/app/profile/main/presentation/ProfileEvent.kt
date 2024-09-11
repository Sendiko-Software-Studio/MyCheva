package com.mycheva.app.profile.main.presentation

sealed class ProfileEvent {
    data class OnLogout(val token: String): ProfileEvent()
    data object OnClearState: ProfileEvent()
    data object OnEditProfile: ProfileEvent()
    data class OnGetProfile(val token: String, val userId: String): ProfileEvent()
    data class OnUsernameEdit(val username: String): ProfileEvent()
    data class OnPasswordEdit(val password: String): ProfileEvent()
    data object OnChangeUsername: ProfileEvent()
    data object OnChangePassword: ProfileEvent()
}