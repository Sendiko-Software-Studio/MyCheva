package com.mycheva.app.announcement

data class AnnouncementScreenState(
    val announcements: List<Announcement> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
)
