package com.mycheva.app.attendance.domain

import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.data.AttendanceResponse
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AttendanceRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
): AttendanceRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun postAttendance(
        token: String,
        request: AttendanceRequest
    ): Result<AttendanceResponse> {
        return suspendCoroutine { continuation ->
            apiServices.postAttendance(token, request)
                .enqueue(
                    object : Callback<AttendanceResponse> {
                        override fun onResponse(
                            call: Call<AttendanceResponse>,
                            response: Response<AttendanceResponse>
                        ) {
                            when (response.code()) {
                                201 -> continuation.resume(Result.success(response.body()!!))

                                else -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(call: Call<AttendanceResponse>, throwable: Throwable) {
                            continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }

                    }
                )
        }
    }
}