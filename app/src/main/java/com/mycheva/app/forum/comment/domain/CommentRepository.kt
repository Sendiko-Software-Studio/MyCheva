package com.mycheva.app.forum.comment.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.comment.data.PostReplyRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CommentRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    fun loadData(token: String, forumId: String)

    fun postReply(token: String, request: PostReplyRequest)

}