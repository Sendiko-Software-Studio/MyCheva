package com.mycheva.app.announcement

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AnnouncementViewModel: ViewModel() {

    private val _state = MutableStateFlow(AnnouncementScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: AnnouncementScreenEvent) {
        when(event) {
            is AnnouncementScreenEvent.OnAddBookmark ->
                TODO()
        }
    }

}