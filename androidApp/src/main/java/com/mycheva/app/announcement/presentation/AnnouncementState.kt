package com.mycheva.app.announcement.presentation

import com.mycheva.app.core.ui.utils.UiText

data class AnnouncementState(
    val announcements: List<AnnouncementUi> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val isRequestError: Boolean = false,
    val token: String = "",
)
