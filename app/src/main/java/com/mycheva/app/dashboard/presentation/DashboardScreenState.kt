package com.mycheva.app.dashboard.presentation

import com.mycheva.app.dashboard.data.EventsItem

data class DashboardScreenState(
    val name: String = "",
    val latestEvent: EventsItem? = null,
    val token: String = "",
    val userId: String = "",
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = "",
    val divisionId: String = "",
)
