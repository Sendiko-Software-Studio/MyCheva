package com.mycheva.app.announcement.presentation

import com.mycheva.app.announcement.data.dto.AnnouncementsItem
import com.mycheva.app.announcement.domain.Announcement

sealed class AnnouncementEvent {
    data class OnAddBookmark(val announcement: Announcement): AnnouncementEvent()
    data class OnLoadAnnouncements(val token: String): AnnouncementEvent()
    data object OnClearState: AnnouncementEvent()
}