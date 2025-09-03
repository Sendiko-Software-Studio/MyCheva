package com.mycheva.app.profile.main.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.profile.main.data.dto.changepassword.ChangePasswordRequest
import com.mycheva.app.profile.main.data.dto.changeusername.ChangeUsernameRequest
import com.mycheva.app.profile.main.data.dto.getuser.toDomain
import com.mycheva.app.profile.main.domain.ProfileRepository
import com.mycheva.app.profile.main.domain.User
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val appPreferences: AppPreferences,
    private val client: KtorClient
) : ProfileRepository {

    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun deleteToken() {
        appPreferences.deleteToken()
    }

    override suspend fun getUser(
        token: String,
        userId: String
    ): Result<User, DataError.Remote> {
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

    override suspend fun logout(token: String): Result<String, DataError.Remote> {
        val response = client.logout(token)

        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.message)
            }
            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

    override suspend fun changeUsername(
        token: String,
        userId: String,
        username: String
    ): Result<String, DataError.Remote> {
        val request = ChangeUsernameRequest(username)
        val response = client.changeUsername(token, userId, request)

        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.message)
            }
            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

    override suspend fun changePassword(
        token: String,
        userId: String,
        password: String,
        oldPassword: String
    ): Result<String, DataError.Remote> {
        val request = ChangePasswordRequest(password, oldPassword)
        val response = client.changePassword(token, userId, request)

        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.message)
            }
            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

}