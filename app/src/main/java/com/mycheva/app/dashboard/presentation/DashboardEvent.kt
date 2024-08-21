package com.mycheva.app.dashboard.presentation

sealed class DashboardEvent {
    data class GetUserData(val token: String, val userId: String) : DashboardEvent()
    data class GetEventData(val token: String): DashboardEvent()
    data object OnClearState: DashboardEvent()
}