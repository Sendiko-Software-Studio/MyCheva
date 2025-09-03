package com.mycheva.app.bookmark.domain

import com.mycheva.app.bookmark.data.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    suspend fun addBookmark(bookmark: Bookmark): Result<Boolean>

    suspend fun removeBookmark(bookmark: Bookmark): Result<Boolean>

    fun getBookmarks(): Flow<List<Bookmark>>

}