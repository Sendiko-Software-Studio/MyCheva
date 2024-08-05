package com.mycheva.app.profile.main.presentation

sealed class ProfileScreenEvent {
    data object OnLogout: ProfileScreenEvent()
    data object OnClearState: ProfileScreenEvent()
    data object OnEditProfile: ProfileScreenEvent()
    data class OnGetProfile(val token: String): ProfileScreenEvent()
}