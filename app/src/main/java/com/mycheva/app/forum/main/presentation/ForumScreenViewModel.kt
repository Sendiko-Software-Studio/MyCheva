package com.mycheva.app.forum.main.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForumScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(ForumScreenState())
    val state= _state.asStateFlow()

    fun onEvent(event: ForumScreenEvent) {
        when (event) {
            ForumScreenEvent.OnClearNotification ->
                _state.update { ForumScreenState() }
        }
    }

}