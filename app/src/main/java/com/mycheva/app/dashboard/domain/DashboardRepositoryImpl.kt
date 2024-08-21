package com.mycheva.app.dashboard.domain

import android.util.Log
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.dashboard.data.GetEventsResponse
import com.mycheva.app.profile.main.data.GetUserResponse
import com.mycheva.app.profile.main.data.User1
import com.mycheva.app.schedule.core.EventsItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DashboardRepositoryImpl @Inject constructor(
    private val apiService: ApiServices,
    private val appPreferences: AppPreferences
) : DashboardRepository {

    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun getUser(token: String, userId: String): Result<User1> {
        return suspendCoroutine {
            apiService.getUser("Bearer $token", id = userId).enqueue(
                object : Callback<GetUserResponse> {
                    override fun onResponse(
                        call: Call<GetUserResponse>,
                        response: Response<GetUserResponse>
                    ) {
                        when (response.code()) {
                            200 -> it.resume(Result.success(response.body()!!.user))
                            else -> it.resume(Result.failure(exception = Exception("Server error.")))
                        }
                    }

                    override fun onFailure(call: Call<GetUserResponse>, throwable: Throwable) {
                        it.resume(Result.failure(exception = Exception("Server error.")))
                    }

                }
            )
        }
    }

    override suspend fun getEvents(token: String): Result<List<EventsItem>> {
        return suspendCoroutine {
            Log.i("GET_EVENT", "DashboardRepoImpl")
            apiService.getEvents("Bearer $token").enqueue(
                object : Callback<GetEventsResponse> {
                    override fun onResponse(
                        call: Call<GetEventsResponse>,
                        response: Response<GetEventsResponse>
                    ) {
                        when (response.code()) {
                            200 -> {
                                it.resume(Result.success(response.body()!!.events))
                            }

                            else -> it.resume(Result.failure(Exception("Server error.")))
                        }
                    }

                    override fun onFailure(call: Call<GetEventsResponse>, throwable: Throwable) {
                        it.resume(Result.failure(Exception("Server error.")))
                    }

                }
            )
        }
    }

}