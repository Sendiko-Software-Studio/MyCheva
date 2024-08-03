package com.mycheva.app.announcement.presentation

import com.mycheva.app.announcement.data.Announcement

data class AnnouncementScreenState(
    val announcements: List<Announcement> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
)
