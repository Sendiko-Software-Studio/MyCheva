package com.mycheva.app.core.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavigationViewModel: ViewModel() {

    private val _state = MutableStateFlow(NavigationState())
    val state = _state.asStateFlow()

    fun onNavigate(screen: Int) {
        _state.update { it.copy(currentScreen = screen) }
    }

}