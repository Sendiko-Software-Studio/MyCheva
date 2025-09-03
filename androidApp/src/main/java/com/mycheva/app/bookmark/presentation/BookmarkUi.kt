package com.mycheva.app.bookmark.presentation

import com.mycheva.app.bookmark.domain.Bookmark

data class BookmarkUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val title: String,
    val content: String,
) {

    fun toDomain(): Bookmark {
        return Bookmark(
            id = this.id,
            profileUrl = this.profileUrl,
            username = this.username,
            time = this.time,
            content = this.content,
            title = this.title
        )
    }
    companion object {
        fun fromDomain(bookmark: Bookmark): BookmarkUi {
            return BookmarkUi(
                id = bookmark.id,
                profileUrl = bookmark.profileUrl,
                username = bookmark.username,
                time = bookmark.time,
                content = bookmark.content,
                title = bookmark.title
            )
        }
    }
}