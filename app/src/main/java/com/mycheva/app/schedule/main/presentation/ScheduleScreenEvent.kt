package com.mycheva.app.schedule.main.presentation

sealed class ScheduleScreenEvent {
    data class OnLoadSchedule(val token: String): ScheduleScreenEvent()
    data object OnClearState: ScheduleScreenEvent()
}