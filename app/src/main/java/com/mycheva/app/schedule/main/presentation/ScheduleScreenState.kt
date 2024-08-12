package com.mycheva.app.schedule.main.presentation

import com.mycheva.app.schedule.core.EventsItem
import java.time.LocalDate

data class ScheduleScreenState(
    val events: Map<LocalDate, List<EventsItem>> = mutableMapOf(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestFailed: Boolean = false,
    val token: String = "",
)
