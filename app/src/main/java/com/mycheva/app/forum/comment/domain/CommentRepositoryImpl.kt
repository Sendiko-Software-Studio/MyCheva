package com.mycheva.app.forum.comment.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.comment.data.PostReplyRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
): CommentRepository {
    override fun getToken(): Flow<String> { return appPreferences.getToken() }

    override fun getUserId(): Flow<String> { return appPreferences.getUserId() }

    override fun loadData(token: String, forumId: String) {
        TODO("Not yet implemented")
    }

    override fun postReply(token: String, request: PostReplyRequest) {
        TODO("Not yet implemented")
    }
}