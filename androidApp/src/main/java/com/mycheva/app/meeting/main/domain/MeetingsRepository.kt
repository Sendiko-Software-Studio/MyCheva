package com.mycheva.app.meeting.main.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.meeting.core.domain.Meeting
import kotlinx.coroutines.flow.Flow

interface MeetingsRepository {

    fun getToken(): Flow<String>

    suspend fun getEvents(token: String): Result<List<Meeting>, DataError.Remote>
}