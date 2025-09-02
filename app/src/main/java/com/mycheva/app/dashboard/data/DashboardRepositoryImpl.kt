package com.mycheva.app.dashboard.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.dashboard.domain.DashboardRepository
import com.mycheva.app.meeting.core.data.toDomain
import com.mycheva.app.meeting.core.domain.Meeting
import com.mycheva.app.profile.main.data.dto.getuser.toDomain
import com.mycheva.app.profile.main.domain.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val client: KtorClient,
    private val appPreferences: AppPreferences,
) : DashboardRepository {

    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun getUser(token: String, userId: String): Result<User, DataError.Remote> {
        val response = client.getUser(token, userId)

        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.user.toDomain())
            }

            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

    override suspend fun getEvents(token: String): Result<List<Meeting>, DataError.Remote> {
        val response = client.getMeetings(token)

        return when (response) {
            is Result.Success -> {
                val data = response.data

                Result.Success(data.events.map { it.toDomain() })
            }

            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

}