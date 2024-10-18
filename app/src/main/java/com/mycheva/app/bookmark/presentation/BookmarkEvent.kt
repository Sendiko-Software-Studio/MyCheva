package com.mycheva.app.bookmark.presentation

import com.mycheva.app.core.database.BookmarkEntity

sealed class BookmarkEvent {
    data class OnRemoveBookmark(val bookmarkEntity: BookmarkEntity): BookmarkEvent()
}
