package com.mycheva.app.bookmark

import com.mycheva.app.announcement.Announcement

data class BookmarkScreenState(
    val announcements: List<Announcement> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
)
