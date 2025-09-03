package com.mycheva.app.attendance.presentation

import com.mycheva.app.core.ui.utils.UiText

data class AttendanceState(
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val userId: String = "",
    val eventId: String = "",
    val token: String = "",
    val isRequestSuccess: Boolean = false,
    val isScanning: Boolean = false
)
