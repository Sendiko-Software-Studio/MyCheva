package com.mycheva.app.profile.main.domain

import com.mycheva.app.profile.main.data.ChangePasswordRequest
import com.mycheva.app.profile.main.data.ChangeUsernameRequest
import com.mycheva.app.core.data.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun deleteToken(): Result<String>

    suspend fun getUser(token: String, userId: String): Result<User>

    suspend fun logout(token: String): Result<String>

    suspend fun changeUsername(token: String, userId: String, request: ChangeUsernameRequest): Result<String>

    suspend fun changePassword(token: String, userId: String, request: ChangePasswordRequest): Result<String>

}