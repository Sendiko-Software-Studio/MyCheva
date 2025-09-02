package com.mycheva.app.dashboard.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.meeting.core.domain.Meeting
import com.mycheva.app.profile.main.domain.User
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun getUser(token: String, userId: String): Result<User, DataError.Remote>

    suspend fun getEvents(token: String): Result<List<Meeting>, DataError.Remote>

}