package com.mycheva.app.meeting.main.domain

import com.mycheva.app.core.data.GetEventsResponse
import kotlinx.coroutines.flow.Flow

interface MeetingsRepository {

    fun getToken(): Flow<String>

    suspend fun getEvents(token: String): Result<GetEventsResponse>
}