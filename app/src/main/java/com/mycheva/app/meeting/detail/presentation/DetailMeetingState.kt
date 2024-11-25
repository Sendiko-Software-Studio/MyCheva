package com.mycheva.app.meeting.detail.presentation

import com.mycheva.app.core.data.EventsItem

data class DetailMeetingState(
    val token: String = "",
    val eventsItem: EventsItem? = null,
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = "",
)
