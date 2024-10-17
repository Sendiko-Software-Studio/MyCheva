package com.mycheva.app.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Upsert
    fun addBookmark(bookmark: BookmarkEntity)

    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Flow<List<BookmarkEntity>>

    @Delete
    fun removeBookmarks(bookmark: BookmarkEntity)
}