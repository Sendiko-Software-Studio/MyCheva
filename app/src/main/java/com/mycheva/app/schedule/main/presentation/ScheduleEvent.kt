package com.mycheva.app.schedule.main.presentation

sealed class ScheduleEvent {
    data class OnLoadSchedule(val token: String): ScheduleEvent()
    data class OnSearchTextChanged(val value: String): ScheduleEvent()
    data object OnClearFilter: ScheduleEvent()
    data object OnClearState: ScheduleEvent()
}