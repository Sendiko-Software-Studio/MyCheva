package com.mycheva.app.meeting.main.presentation

sealed class MeetingsAction {
    data class OnLoadSchedule(val token: String): MeetingsAction()
    data class OnSearchTextChanged(val value: String): MeetingsAction()
    data object OnClearFilter: MeetingsAction()
    data object OnClearState: MeetingsAction()
}