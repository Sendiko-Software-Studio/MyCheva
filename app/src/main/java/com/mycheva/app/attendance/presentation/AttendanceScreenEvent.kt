package com.mycheva.app.attendance.presentation

sealed class AttendanceScreenEvent {
    data class OnEventIdRead(val token: String, val eventId: String, val userId: String): AttendanceScreenEvent()
    data object OnClearState: AttendanceScreenEvent()
}