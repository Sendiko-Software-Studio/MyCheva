package com.mycheva.app.profile.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            ProfileScreenEvent.OnClearState -> TODO()
            ProfileScreenEvent.OnEditProfile -> TODO()
            ProfileScreenEvent.OnLogout -> TODO()
        }
    }

}