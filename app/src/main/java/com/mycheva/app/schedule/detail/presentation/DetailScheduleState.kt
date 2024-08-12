package com.mycheva.app.schedule.detail.presentation

import com.mycheva.app.schedule.core.EventsItem

data class DetailScheduleState(
    val token: String = "",
    val eventsItem: EventsItem? = null,
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = "",
)
