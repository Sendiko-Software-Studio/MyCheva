package com.mycheva.app.schedule.main.presentation

import com.mycheva.app.core.data.EventsItem

data class ScheduleScreenState(
    val events: List<EventsItem> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestFailed: Boolean = false,
    val token: String = "",
    val searchText: String = "",
)
