package com.mycheva.app.attendance.presentation

sealed class AttendanceEvent {
    data class OnEventIdRead(val token: String, val eventId: String, val userId: String): AttendanceEvent()
    data object OnClearState: AttendanceEvent()
}