package com.mycheva.app.profile.main.domain

import NOT_FOUND
import SERVER_ERROR
import UNAUTHORIZED
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.profile.main.data.ChangePasswordRequest
import com.mycheva.app.profile.main.data.ChangePasswordResponse
import com.mycheva.app.profile.main.data.ChangeUsernameRequest
import com.mycheva.app.profile.main.data.ChangeUsernameResponse
import com.mycheva.app.profile.main.data.GetUserResponse
import com.mycheva.app.profile.main.data.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ProfileRepositoryImpl @Inject constructor(
    private val appPreferences: AppPreferences,
    private val apiServices: ApiServices
): ProfileRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun deleteToken(): Result<String> {
        appPreferences.deleteToken()
        return Result.success("Logout success.")
    }

    override suspend fun getUser(token: String, userId: String): Result<User> {
        return suspendCoroutine { continuation ->
            apiServices.getUser(token = token, id = userId).enqueue(
                object : Callback<GetUserResponse> {
                    override fun onResponse(
                        call: Call<GetUserResponse>,
                        response: Response<GetUserResponse>
                    ) {
                        when (response.code()) {
                            200 -> continuation.resume(Result.success(response.body()!!.user))
                            401 -> continuation.resume(Result.failure(Exception(UNAUTHORIZED)))
                            404 -> continuation.resume(Result.failure(Exception(NOT_FOUND)))
                            500 -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }
                    }

                    override fun onFailure(
                        call: Call<GetUserResponse>,
                        throwable: Throwable
                    ) {
                        continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                    }

                }
            )
        }
    }

    override suspend fun logout(token: String): Result<String> {
        appPreferences.getToken()
        return Result.success("Logout success.")
    }

    override suspend fun changeUsername(
        token: String,
        userId: String,
        request: ChangeUsernameRequest
    ): Result<String> {
        return suspendCoroutine { continuation ->
            apiServices.changeUsername(token, userId, request)
                .enqueue(
                    object : Callback<ChangeUsernameResponse> {
                        override fun onResponse(
                            call: Call<ChangeUsernameResponse>,
                            response: Response<ChangeUsernameResponse>
                        ) {
                            when (response.code()) {
                                200 -> continuation.resume(Result.success(response.body()!!.message))
                                401 -> continuation.resume(Result.failure(Exception(UNAUTHORIZED)))
                                404 -> continuation.resume(Result.failure(Exception(NOT_FOUND)))
                                500 -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(
                            call: Call<ChangeUsernameResponse>,
                            throwable: Throwable
                        ) {
                            continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }

                    }
                )
        }
    }

    override suspend fun changePassword(
        token: String,
        userId: String,
        request: ChangePasswordRequest
    ): Result<String> {
        return suspendCoroutine { continuation ->
            apiServices.changePassword(token, userId, request)
                .enqueue(
                    object : Callback<ChangePasswordResponse> {
                        override fun onResponse(
                            call: Call<ChangePasswordResponse>,
                            response: Response<ChangePasswordResponse>
                        ) {
                            when (response.code()) {
                                200 -> continuation.resume(Result.success(response.body()!!.message))
                                401 -> continuation.resume(Result.failure(Exception(UNAUTHORIZED)))
                                404 -> continuation.resume(Result.failure(Exception(NOT_FOUND)))
                                500 -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(
                            call: Call<ChangePasswordResponse>,
                            throwable: Throwable
                        ) {
                            continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }

                    }
                )
        }
    }

}