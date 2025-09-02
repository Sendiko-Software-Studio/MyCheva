package com.mycheva.app.login.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.login.data.dto.LoginRequest
import com.mycheva.app.login.data.dto.toDomain
import com.mycheva.app.login.domain.LoginRepository
import com.mycheva.app.login.domain.UserWithToken

class LoginRepositoryImpl(
    private val appPreferences: AppPreferences,
    private val client: KtorClient,
) : LoginRepository {
    override suspend fun login(
        username: String,
        password: String,
    ): Result<UserWithToken, DataError.Remote> {
        val request = LoginRequest(password, username)
        val response = client.login(request)
            .onError {

            }

        return when (response) {
            is Result.Success -> Result.Success(response.data.toDomain())
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun saveToken(token: String) = appPreferences.saveToken(token)

    override suspend fun saveUserId(userId: String) = appPreferences.saveUserId(userId)

}