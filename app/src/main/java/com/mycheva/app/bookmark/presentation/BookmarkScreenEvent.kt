package com.mycheva.app.bookmark.presentation

import com.mycheva.app.announcement.data.AnnouncementsItem

sealed class BookmarkScreenEvent {
    data class OnRemoveBookmark(val announcement: AnnouncementsItem): BookmarkScreenEvent()
}
