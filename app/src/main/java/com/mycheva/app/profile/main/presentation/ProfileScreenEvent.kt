package com.mycheva.app.profile.main.presentation

sealed class ProfileScreenEvent {
    data class OnLogout(val token: String): ProfileScreenEvent()
    data object OnClearState: ProfileScreenEvent()
    data object OnEditProfile: ProfileScreenEvent()
    data class OnGetProfile(val token: String, val userId: String): ProfileScreenEvent()
    data class OnUsernameEdit(val username: String): ProfileScreenEvent()
    data class OnPasswordEdit(val password: String): ProfileScreenEvent()
    data object OnChangeUsername: ProfileScreenEvent()
    data object OnChangePassword: ProfileScreenEvent()
}