package com.mycheva.app.meeting.detail.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.meeting.core.data.toDomain
import com.mycheva.app.meeting.core.domain.Meeting
import com.mycheva.app.meeting.detail.domain.DetailMeetingRepository
import kotlinx.coroutines.flow.Flow

class DetailMeetingRepositoryImpl(
    private val client: KtorClient,
    private val appPreferences: AppPreferences
): DetailMeetingRepository {

    override fun getToken(): Flow<String> = appPreferences.getToken()
    override suspend fun getMeeting(
        token: String,
        meetingId: String,
    ): Result<Meeting, DataError.Remote> {
        val response = client.getMeeting(token, meetingId)

        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.event.toDomain())
            }
            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }


}