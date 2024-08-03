package com.mycheva.app.bookmark

import com.mycheva.app.announcement.Announcement
import com.mycheva.app.announcement.AnnouncementScreenEvent

sealed class BookmarkScreenEvent {
    data class OnRemoveBookmark(val announcement: Announcement): BookmarkScreenEvent()
}
