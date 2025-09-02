package com.mycheva.app.profile.main.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun deleteToken()

    suspend fun getUser(
        token: String,
        userId: String
    ): Result<User, DataError.Remote>

    suspend fun logout(token: String): Result<String, DataError.Remote>

    suspend fun changeUsername(
        token: String,
        userId: String,
        username: String
    ): Result<String, DataError.Remote>

    suspend fun changePassword(
        token: String,
        userId: String,
        password: String,
        oldPassword: String,
    ): Result<String, DataError.Remote>

}