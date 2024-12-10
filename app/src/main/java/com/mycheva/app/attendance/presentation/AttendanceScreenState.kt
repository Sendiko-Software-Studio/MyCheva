package com.mycheva.app.attendance.presentation

data class AttendanceScreenState(
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = "",
    val userId: String = "",
    val eventId: String = "",
    val token: String = "",
    val isRequestSuccess: Boolean = false,
    val isScanning: Boolean = false
)
