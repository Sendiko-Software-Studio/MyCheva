package com.mycheva.app.bookmark.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookmarkScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(BookmarkScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: BookmarkScreenEvent) {
        when (event) {
            is BookmarkScreenEvent.OnRemoveBookmark -> TODO()
        }
    }

}