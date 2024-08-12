package com.mycheva.app.bookmark.presentation

import com.mycheva.app.announcement.data.AnnouncementsItem

data class BookmarkScreenState(
    val announcements: List<AnnouncementsItem> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
)
