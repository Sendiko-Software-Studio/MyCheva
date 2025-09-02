package com.mycheva.app.meeting.main.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.meeting.core.data.toDomain
import com.mycheva.app.meeting.core.domain.Meeting
import com.mycheva.app.meeting.main.domain.MeetingsRepository
import kotlinx.coroutines.flow.Flow

class MeetingsRepositoryImpl(
    private val client: KtorClient,
    private val appPreferences: AppPreferences
): MeetingsRepository {
    override fun getToken(): Flow<String> = appPreferences.getToken()
    override suspend fun getEvents(token: String): Result<List<Meeting>, DataError.Remote> {
        val response = client.getMeetings(token)
        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.events.map { it.toDomain() })
            }

            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

}