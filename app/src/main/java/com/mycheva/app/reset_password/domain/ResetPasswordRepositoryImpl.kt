package com.mycheva.app.reset_password.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.NOT_FOUND
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.reset_password.data.ResetPasswordRequest
import com.mycheva.app.reset_password.data.ResetPasswordResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ResetPasswordRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
): ResetPasswordRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun resetPassword(token: String, request: ResetPasswordRequest): Result<ResetPasswordResponse> {
        return suspendCoroutine { continuation ->
            apiServices.resetPassword(token, request)
                .enqueue(
                    object : Callback<ResetPasswordResponse> {
                        override fun onResponse(
                            call: Call<ResetPasswordResponse>,
                            response: Response<ResetPasswordResponse>
                        ) {
                            when (response.code()) {
                                200 -> continuation.resume(Result.success(response.body()!!))
                                404 -> continuation.resume(Result.failure(Exception(NOT_FOUND)))
                                500 -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(
                            call: Call<ResetPasswordResponse>,
                            throwable: Throwable
                        ) {
                            continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }
                    }
                )
        }
    }


}