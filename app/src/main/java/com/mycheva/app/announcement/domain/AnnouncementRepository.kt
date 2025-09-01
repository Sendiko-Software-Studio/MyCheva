package com.mycheva.app.announcement.domain

import com.mycheva.app.announcement.data.dto.AnnouncementResponse
import com.mycheva.app.core.database.BookmarkEntity
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {

    fun getToken(): Flow<String>

    suspend fun getAnnouncements(token: String): Result<List<Announcement>, DataError.Remote>

    suspend fun addBookmark(announcement: Announcement): Boolean

}