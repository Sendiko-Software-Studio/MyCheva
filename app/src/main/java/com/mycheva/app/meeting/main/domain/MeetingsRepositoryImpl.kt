package com.mycheva.app.meeting.main.domain

import com.mycheva.app.core.data.GetEventsResponse
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.NOT_FOUND
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MeetingsRepositoryImpl @Inject constructor(
    private val apiService: ApiServices,
    private val appPreferences: AppPreferences
): MeetingsRepository {
    override fun getToken(): Flow<String> = appPreferences.getToken()

    override suspend fun getEvents(token: String): Result<GetEventsResponse> {
        return suspendCoroutine { continuation ->
            apiService.getEvents(token)
                .enqueue(
                    object : Callback<GetEventsResponse> {
                        override fun onResponse(
                            call: Call<GetEventsResponse>,
                            response: Response<GetEventsResponse>
                        ) {
                            when(response.code()) {
                                200 -> continuation.resume(Result.success(response.body()!!))
                                401 -> continuation.resume(Result.failure(Exception(NOT_FOUND)))
                                else -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(call: Call<GetEventsResponse>, t: Throwable) {
                            continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }
                    }
                )
        }
    }
}