package com.mycheva.app.announcement.presentation

import com.mycheva.app.announcement.data.AnnouncementsItem

data class AnnouncementState(
    val announcements: List<AnnouncementsItem> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
    val token: String = "",
)
