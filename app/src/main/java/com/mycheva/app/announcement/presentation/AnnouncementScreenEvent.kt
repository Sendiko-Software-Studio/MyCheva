package com.mycheva.app.announcement.presentation

import com.mycheva.app.announcement.data.Announcement

sealed class AnnouncementScreenEvent {
    data class OnAddBookmark(val announcement: Announcement): AnnouncementScreenEvent()
}