package com.mycheva.app.dashboard.presentation

sealed class DashboardScreenEvent {
    data class OnGetDashboardData(val token: String, val userId: String) : DashboardScreenEvent()
    data object OnClearState: DashboardScreenEvent()
}