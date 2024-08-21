package com.mycheva.app.dashboard.presentation

import com.mycheva.app.schedule.core.EventsItem

data class DashboardState(
    val name: String = "",
    val latestEvent: EventsItem? = null,
    val token: String = "",
    val userId: String = "",
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = "",
    val divisionId: String = "",
)
