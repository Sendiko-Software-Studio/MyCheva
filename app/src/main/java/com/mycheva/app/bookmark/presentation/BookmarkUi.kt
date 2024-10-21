package com.mycheva.app.bookmark.presentation

import com.mycheva.app.core.database.BookmarkEntity

data class BookmarkUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val content: String,
) {
    companion object {
        fun toBookmarkUi(bookmarkEntity: BookmarkEntity): BookmarkUi {
            return BookmarkUi(
                id = bookmarkEntity.id.toString(),
                profileUrl = bookmarkEntity.profileUrl,
                username = bookmarkEntity.name,
                time = bookmarkEntity.timeStamp,
                content = bookmarkEntity.content
            )
        }
    }
}