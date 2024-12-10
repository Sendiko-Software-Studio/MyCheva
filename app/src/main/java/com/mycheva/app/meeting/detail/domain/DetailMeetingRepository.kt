package com.mycheva.app.meeting.detail.domain

import com.mycheva.app.meeting.detail.data.GetMeetingResponse
import kotlinx.coroutines.flow.Flow

interface DetailMeetingRepository {

    fun getToken(): Flow<String>

    suspend fun getEvent(token: String, eventId: String): Result<GetMeetingResponse>

}