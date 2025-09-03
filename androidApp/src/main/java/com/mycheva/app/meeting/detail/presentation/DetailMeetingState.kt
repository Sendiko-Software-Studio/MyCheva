package com.mycheva.app.meeting.detail.presentation

import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.meeting.main.presentation.MeetingUi

data class DetailMeetingState(
    val token: String = "",
    val meeting: MeetingUi? = null,
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
)
