package com.mycheva.app.profile.presentation

sealed class ProfileScreenEvent {
    data object OnLogout: ProfileScreenEvent()
    data object OnClearState: ProfileScreenEvent()
    data object OnEditProfile: ProfileScreenEvent()
}