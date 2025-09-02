package com.mycheva.app.reset_password.data

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.NOT_FOUND
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.network.utils.SERVER_ERROR
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.reset_password.data.dto.ResetPasswordRequest
import com.mycheva.app.reset_password.data.dto.ResetPasswordResponse
import com.mycheva.app.reset_password.domain.ResetPasswordRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ResetPasswordRepositoryImpl(
    private val client: KtorClient,
    private val appPreferences: AppPreferences
): ResetPasswordRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun resetPassword(
        token: String,
        email: String,
    ): Result<String, DataError.Remote> {
        val request = ResetPasswordRequest(email)
        val response = client.resetPassword(token, request)

        return when(response) {
            is Result.Success -> {
                Result.Success(response.data.message)
            }
            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

}