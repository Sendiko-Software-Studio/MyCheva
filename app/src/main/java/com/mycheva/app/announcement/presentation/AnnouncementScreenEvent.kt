package com.mycheva.app.announcement.presentation

import com.mycheva.app.announcement.data.AnnouncementsItem

sealed class AnnouncementScreenEvent {
    data class OnAddBookmark(val announcement: AnnouncementsItem): AnnouncementScreenEvent()
    data class OnLoadAnnouncements(val token: String): AnnouncementScreenEvent()
    data object OnClearState: AnnouncementScreenEvent()
}