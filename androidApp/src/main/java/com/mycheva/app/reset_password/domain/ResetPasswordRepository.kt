package com.mycheva.app.reset_password.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.reset_password.data.dto.ResetPasswordRequest
import com.mycheva.app.reset_password.data.dto.ResetPasswordResponse
import kotlinx.coroutines.flow.Flow

interface ResetPasswordRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun resetPassword(token: String, email: String): Result<String, DataError.Remote>

}