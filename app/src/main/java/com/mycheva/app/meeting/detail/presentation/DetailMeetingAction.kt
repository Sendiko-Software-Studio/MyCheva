package com.mycheva.app.meeting.detail.presentation

sealed class DetailMeetingAction {
    data class OnLoadSchedule(val token: String, val eventId: String): DetailMeetingAction()
    data object OnClearState: DetailMeetingAction()
}