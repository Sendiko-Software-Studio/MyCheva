package com.mycheva.app.splashscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.splashscreen.data.SplashScreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


class SplashScreenViewModel(
    repository: SplashScreenRepository,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(SplashScreenState())
    val state = combine(_token, _state) { token, state ->
        state.copy(
            token = token
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SplashScreenState())

}