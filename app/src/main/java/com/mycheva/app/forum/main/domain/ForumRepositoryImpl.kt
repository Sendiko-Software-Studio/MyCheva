package com.mycheva.app.forum.main.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.core.network.UNAUTHORIZED
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.core.data.Forum
import com.mycheva.app.forum.main.data.GetForumsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ForumRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
): ForumRepository {

    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override suspend fun getForums(token: String): Result<List<Forum>> {
        return suspendCoroutine { continuation ->
            apiServices.getForums(token)
                .enqueue(
                    object: Callback<GetForumsResponse> {
                        override fun onResponse(
                            call: Call<GetForumsResponse>,
                            response: Response<GetForumsResponse>
                        ) {
                            when(response.code()) {
                                200 -> continuation
                                    .resume(Result.success(response.body()!!.forums))
                                401 -> continuation
                                    .resume(Result.failure(Exception(UNAUTHORIZED)))
                                500 -> continuation
                                    .resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(
                            call: Call<GetForumsResponse>,
                            t: Throwable
                        ) {
                            continuation
                                .resume(Result.failure(Exception(SERVER_ERROR)))
                        }

                    }
                )
        }
    }

}