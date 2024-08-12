package com.mycheva.app.dashboard.presentation

sealed class DashboardScreenEvent {
    data class GetUserData(val token: String, val userId: String) : DashboardScreenEvent()
    data class GetEventData(val token: String): DashboardScreenEvent()
    data object OnClearState: DashboardScreenEvent()
}