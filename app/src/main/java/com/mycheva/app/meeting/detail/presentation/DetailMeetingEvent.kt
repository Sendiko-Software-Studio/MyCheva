package com.mycheva.app.meeting.detail.presentation

sealed class DetailMeetingEvent {
    data class OnLoadSchedule(val token: String, val eventId: String): DetailMeetingEvent()
    data object OnClearState: DetailMeetingEvent()
}