package com.mycheva.app.dashboard.domain

import com.mycheva.app.profile.main.data.User
import com.mycheva.app.schedule.core.EventsItem
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun getUser(token: String, userId: String): Result<User>

    suspend fun getEvents(token: String): Result<List<EventsItem>>

}