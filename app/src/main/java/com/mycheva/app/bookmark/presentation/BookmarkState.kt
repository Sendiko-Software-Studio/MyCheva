package com.mycheva.app.bookmark.presentation

import com.mycheva.app.core.database.BookmarkEntity

data class BookmarkState(
    val bookmarks: List<BookmarkEntity> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
)
