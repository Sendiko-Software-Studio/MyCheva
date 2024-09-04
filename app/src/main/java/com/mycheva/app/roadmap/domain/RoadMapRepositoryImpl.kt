package com.mycheva.app.roadmap.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.roadmap.data.GetRoadMapResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RoadMapRepositoryImpl @Inject constructor(
    private val appPreferences: AppPreferences,
    private val apiServices: ApiServices
): RoadMapRepository {

    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override suspend fun getRoadMaps(token: String): Result<GetRoadMapResponse> {
        return suspendCoroutine { continuation ->
            apiServices.getRoadMap(token).enqueue(
                object : Callback<GetRoadMapResponse> {
                    override fun onResponse(
                        call: Call<GetRoadMapResponse>,
                        response: Response<GetRoadMapResponse>
                    ) {
                        when (response.code()) {
                            200 -> continuation.resume(Result.success(response.body()!!))
                            500 -> continuation.resume(Result.failure(Exception("Server error.")))
                        }
                    }

                    override fun onFailure(
                        call: Call<GetRoadMapResponse>,
                        throwable: Throwable
                    ) {
                        continuation.resume(Result.failure(Exception("Server error.")))
                    }

                }
            )
        }
    }

}