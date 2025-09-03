package com.mycheva.app.dashboard.presentation

import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.meeting.main.presentation.MeetingUi

data class DashboardState(
    val name: String = "",
    val latestEvent: MeetingUi? = null,
    val token: String = "",
    val userId: String = "",
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val divisionId: String = "",
)
