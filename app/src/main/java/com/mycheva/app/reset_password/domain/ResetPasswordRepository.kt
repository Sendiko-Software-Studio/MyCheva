package com.mycheva.app.reset_password.domain

import com.mycheva.app.reset_password.data.ResetPasswordRequest
import com.mycheva.app.reset_password.data.ResetPasswordResponse
import kotlinx.coroutines.flow.Flow

interface ResetPasswordRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun resetPassword(token: String, request: ResetPasswordRequest): Result<ResetPasswordResponse>

}