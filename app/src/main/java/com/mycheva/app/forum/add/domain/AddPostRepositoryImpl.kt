package com.mycheva.app.forum.add.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.core.network.UNAUTHORIZED
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.add.data.ForumPostRequest
import com.mycheva.app.forum.add.data.ForumPostResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AddPostRepositoryImpl@Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
) : AddPostRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun postForum(token: String, request: ForumPostRequest): Result<ForumPostResponse> {
        return suspendCoroutine { continuation ->
            apiServices.postForum(token, request)
                .enqueue(
                    object: Callback<ForumPostResponse> {
                        override fun onResponse(
                            call: Call<ForumPostResponse>,
                            response: Response<ForumPostResponse>
                        ) {
                            when(response.code()) {
                                200 -> continuation.resume(Result.success(response.body()!!))
                                401 -> continuation.resume(Result.failure(Exception(UNAUTHORIZED)))
                                else -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                            }
                        }

                        override fun onFailure(call: Call<ForumPostResponse>, t: Throwable) {
                            continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }

                    }
                )
        }
    }


}