package com.mycheva.app.meeting.detail.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.NOT_FOUND
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.core.network.UNAUTHORIZED
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.meeting.detail.data.GetMeetingResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DetailMeetingRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
): DetailMeetingRepository {

    override fun getToken(): Flow<String> = appPreferences.getToken()

    override suspend fun getEvent(token: String, eventId: String): Result<GetMeetingResponse> {
        return suspendCoroutine { continuation ->
            apiServices.getEvent(token, eventId)
                .enqueue(
                    object : Callback<GetMeetingResponse> {
                        override fun onResponse(
                            call: Call<GetMeetingResponse>,
                            response: Response<GetMeetingResponse>
                        ) {
                            when(response.code()) {
                                200 -> continuation.resume(Result.success(response.body()!!))
                                401 -> continuation.resume(Result.failure(Exception(UNAUTHORIZED)))
                                404 -> continuation.resume(Result.failure(Exception(NOT_FOUND)))
                                else -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(call: Call<GetMeetingResponse>, t: Throwable) {
                            continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }
                    }
                )
        }
    }


}