package com.mycheva.app.dashboard.domain

import com.mycheva.app.profile.main.data.User1
import com.mycheva.app.schedule.core.EventsItem
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun getUser(token: String, userId: String): Result<User1>

    suspend fun getEvents(token: String): Result<List<EventsItem>>

}