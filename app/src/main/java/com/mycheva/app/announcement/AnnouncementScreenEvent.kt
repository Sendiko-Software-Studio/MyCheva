package com.mycheva.app.announcement

sealed class AnnouncementScreenEvent {
    data class OnAddBookmark(val announcement: Announcement): AnnouncementScreenEvent()
}