package com.mycheva.app.dashboard.presentation

data class DashboardScreenState(
    val name: String = "",
    val latestEvent: Any? = null,
    val token: String = "",
    val userId: String = "",
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = ""
)
