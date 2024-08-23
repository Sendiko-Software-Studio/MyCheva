package com.mycheva.app.login.domain

import NOT_FOUND
import SERVER_ERROR
import UNAUTHORIZED
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepositoryImpl @Inject constructor(
    private val appPreferences: AppPreferences,
    private val apiServices: ApiServices
): LoginRepository {

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return suspendCoroutine { continuation ->
            apiServices.login(loginRequest).enqueue(
                object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        when (response.code()) {
                            200 -> continuation.resume(Result.success(response.body()!!))
                            401 -> continuation.resume(Result.failure(Exception(UNAUTHORIZED)))
                            404 -> continuation.resume(Result.failure(Exception(NOT_FOUND)))
                            500 -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }
                    }

                    override fun onFailure(
                        call: Call<LoginResponse>,
                        throwable: Throwable
                    ) {
                        continuation.resume(Result.failure(Exception(SERVER_ERROR   )))
                    }

                }
            )
        }
    }

    override suspend fun saveToken(token: String) = appPreferences.saveToken(token)

    override suspend fun saveUserId(userId: String) = appPreferences.saveUserId(userId)

}