package com.mycheva.app.bookmark.presentation

import com.mycheva.app.announcement.data.Announcement

sealed class BookmarkScreenEvent {
    data class OnRemoveBookmark(val announcement: Announcement): BookmarkScreenEvent()
}
