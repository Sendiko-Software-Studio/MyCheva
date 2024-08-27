package com.mycheva.app.schedule.main.presentation

sealed class ScheduleScreenEvent {
    data class OnLoadSchedule(val token: String): ScheduleScreenEvent()
    data class OnSearchTextChanged(val value: String): ScheduleScreenEvent()
    data object OnClearFilter: ScheduleScreenEvent()
    data object OnClearState: ScheduleScreenEvent()
}