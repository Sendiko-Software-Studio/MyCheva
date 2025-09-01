package com.mycheva.app.bookmark.presentation

data class BookmarkState(
    val bookmarks: List<BookmarkUi> = emptyList(),
    val isLoading: Boolean = false,
    val notificationMessage: String = "",
    val isRequestError: Boolean = false,
)
