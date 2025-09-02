package com.mycheva.app.meeting.main.presentation

sealed class MeetingEvent {
    data class OnLoadSchedule(val token: String): MeetingEvent()
    data class OnSearchTextChanged(val value: String): MeetingEvent()
    data object OnClearFilter: MeetingEvent()
    data object OnClearState: MeetingEvent()
}