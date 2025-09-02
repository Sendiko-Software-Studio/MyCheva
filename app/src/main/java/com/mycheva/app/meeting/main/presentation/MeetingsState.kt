package com.mycheva.app.meeting.main.presentation

import com.mycheva.app.core.ui.utils.UiText

data class MeetingsState(
    val meetings: List<MeetingUi> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val isRequestFailed: Boolean = false,
    val token: String = "",
    val searchText: String = "",
)
