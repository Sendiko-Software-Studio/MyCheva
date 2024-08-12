package com.mycheva.app.schedule.detail.presentation

sealed class DetailScheduleEvent {
    data class OnLoadSchedule(val token: String, val eventId: String): DetailScheduleEvent()
    data object OnClearState: DetailScheduleEvent()
}