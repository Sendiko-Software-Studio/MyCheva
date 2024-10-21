package com.mycheva.app.announcement.presentation

import com.mycheva.app.announcement.data.AnnouncementsItem

sealed class AnnouncementEvent {
    data class OnAddBookmark(val announcement: AnnouncementsItem): AnnouncementEvent()
    data class OnLoadAnnouncements(val token: String): AnnouncementEvent()
    data object OnClearState: AnnouncementEvent()
}