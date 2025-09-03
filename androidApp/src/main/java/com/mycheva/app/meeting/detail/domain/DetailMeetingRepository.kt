package com.mycheva.app.meeting.detail.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.meeting.core.domain.Meeting
import kotlinx.coroutines.flow.Flow

interface DetailMeetingRepository {

    fun getToken(): Flow<String>

    suspend fun getMeeting(token: String, meetingId: String): Result<Meeting, DataError.Remote>

}