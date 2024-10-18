package com.mycheva.app.announcement.domain

import com.mycheva.app.announcement.data.AnnouncementsItem
import com.mycheva.app.core.database.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {

    fun getToken(): Flow<String>

    suspend fun getAnnouncements(token: String): Result<List<AnnouncementsItem>>

    suspend fun addBookmark(bookmark: BookmarkEntity): Boolean

}