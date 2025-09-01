package com.mycheva.app.announcement.domain

import android.util.Log
import com.mycheva.app.announcement.data.AnnouncementResponse
import com.mycheva.app.announcement.data.AnnouncementsItem
import com.mycheva.app.core.database.BookmarkDao
import com.mycheva.app.core.database.BookmarkEntity
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.core.network.UNAUTHORIZED
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AnnouncementRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences,
    private val dao: BookmarkDao
): AnnouncementRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override suspend fun getAnnouncements(token: String): Result<List<AnnouncementsItem>> {
        return suspendCoroutine { continuation ->
            apiServices.getAnnouncements(token)
                .enqueue(
                    object : retrofit2.Callback<AnnouncementResponse> {
                        override fun onResponse(
                            call: Call<AnnouncementResponse?>,
                            response: Response<AnnouncementResponse?>
                        ) {
                            when(response.code()) {
                                200 -> continuation.resume(Result.success(response.body()!!.announcements))
                                401 -> continuation.resume(Result.failure(Exception(UNAUTHORIZED)))
                                500 -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(
                            call: Call<AnnouncementResponse?>,
                            t: Throwable
                        ) {
                            continuation.resume(Result.failure(Exception(t.message)))
                        }

                    }
                )
        }
    }

    override suspend fun addBookmark(bookmark: BookmarkEntity): Boolean {
        try {
            dao.addBookmark(bookmark)
            return true
        } catch (exception: Exception) {
            Log.e("BOOKMARK", "addBookmark: Error saving to bookmark", exception)
            return false
        }
    }


}