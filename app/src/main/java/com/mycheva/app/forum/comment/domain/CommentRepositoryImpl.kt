package com.mycheva.app.forum.comment.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.NOT_FOUND
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.comment.data.GetForumResponse
import com.mycheva.app.forum.comment.data.PostReplyRequest
import com.mycheva.app.forum.comment.data.PostReplyResponse
import com.mycheva.app.forum.core.data.ForumsItem
import com.mycheva.app.forum.core.data.RepliesItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CommentRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
): CommentRepository {
    override fun getToken(): Flow<String> { return appPreferences.getToken() }

    override fun getUserId(): Flow<String> { return appPreferences.getUserId() }

    override suspend fun loadData(token: String, forumId: String): Result<Pair<ForumsItem, List<RepliesItem>>> {
        return suspendCoroutine { continuation ->
            apiServices.getForum(token, forumId).enqueue(
                object : Callback<GetForumResponse> {
                    override fun onResponse(
                        call: Call<GetForumResponse>,
                        response: Response<GetForumResponse>
                    ) {
                        when(response.code()) {
                            200 -> {
                                val forum = response.body()!!.forum
                                val replies = response.body()!!.forum.replies
                                continuation.resume(Result.success(Pair(forum, replies)))
                            }

                            404 -> continuation.resume(Result.failure(Exception(NOT_FOUND)))

                            else -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }
                    }

                    override fun onFailure(call: Call<GetForumResponse>, t: Throwable) {
                        continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                    }

                }
            )
        }
    }

    override suspend fun postReply(token: String, request: PostReplyRequest): Result<PostReplyResponse> {
        return suspendCoroutine { continuation ->
            apiServices.postReply(token, request).enqueue(
                object : Callback<PostReplyResponse> {
                    override fun onResponse(
                        call: Call<PostReplyResponse>,
                        response: Response<PostReplyResponse>
                    ) {
                        when(response.code()) {
                            200 -> continuation.resume(Result.success(response.body()!!))
                            else -> continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                        }
                    }

                    override fun onFailure(call: Call<PostReplyResponse>, t: Throwable) {
                        continuation.resume(Result.failure(Exception(SERVER_ERROR)))
                    }

                }
            )
        }
    }
}