package com.mycheva.app.bookmark.domain

import com.mycheva.app.core.database.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    suspend fun addBookmark(bookmarkEntity: BookmarkEntity): Result<Boolean>

    suspend fun removeBookmark(bookmarkEntity: BookmarkEntity): Result<Boolean>

    fun getBookmarks(): Flow<List<BookmarkEntity>>

}