package com.mycheva.app.announcement.data

import android.util.Log
import com.mycheva.app.announcement.data.dto.toDomain
import com.mycheva.app.announcement.domain.Announcement
import com.mycheva.app.announcement.domain.AnnouncementRepository
import com.mycheva.app.bookmark.data.BookmarkDao
import com.mycheva.app.bookmark.data.BookmarkEntity
import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow

class AnnouncementRepositoryImpl(
    private val client: KtorClient,
    private val appPreferences: AppPreferences,
    private val dao: BookmarkDao
): AnnouncementRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override suspend fun getAnnouncements(token: String): Result<List<Announcement>, DataError.Remote> {
        val response = client.getAnnouncements(token)

        return when (response) {
            is Result.Success -> {
                val data = response.data
                Result.Success(data.announcements.map { it.toDomain() })
            }
            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

    override suspend fun addBookmark(announcement: Announcement): Boolean {
        try {
            val data = BookmarkEntity(
                profileUrl = announcement.profileUrl,
                name = announcement.username,
                timeStamp = announcement.timeStamp,
                imageUrl = announcement.imageUrl,
                title = announcement.title,
                content = announcement.content
            )
            dao.addBookmark(data)
            return true
        } catch (e: Exception) {
            Log.e("BOOKMARK", "addBookmark: Error saving to bookmark", e)
            return false
        }
    }


}