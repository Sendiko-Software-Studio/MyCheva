package com.mycheva.app.bookmark.presentation

import com.mycheva.app.bookmark.data.BookmarkEntity
import com.mycheva.app.bookmark.domain.Bookmark

sealed class BookmarkEvent {
    data class OnRemoveBookmark(val bookmark: Bookmark): BookmarkEvent()
}
