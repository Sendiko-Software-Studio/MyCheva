package com.mycheva.app.announcement.data

import android.util.Log
import com.mycheva.app.announcement.data.dto.AnnouncementResponse
import com.mycheva.app.announcement.domain.AnnouncementRepository
import com.mycheva.app.core.database.BookmarkDao
import com.mycheva.app.core.database.BookmarkEntity
import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnnouncementRepositoryImpl(
    private val client: KtorClient,
    private val appPreferences: AppPreferences,
    private val dao: BookmarkDao
): AnnouncementRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override suspend fun getAnnouncements(token: String): Result<AnnouncementResponse, DataError.Remote> {
        return client.getAnnouncements(token)
    }

    override suspend fun addBookmark(bookmark: BookmarkEntity): Boolean {
        try {
            dao.addBookmark(bookmark)
            return true
        } catch (e: Exception) {
            Log.e("BOOKMARK", "addBookmark: Error saving to bookmark", e)
            return false
        }
    }


}