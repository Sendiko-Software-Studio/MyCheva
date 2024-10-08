package com.mycheva.app.forum.comment.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.comment.data.GetForumResponse
import com.mycheva.app.forum.comment.data.PostReplyRequest
import com.mycheva.app.forum.comment.data.PostReplyResponse
import com.mycheva.app.forum.core.data.ForumsItem
import com.mycheva.app.forum.core.data.RepliesItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CommentRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun loadData(token: String, forumId: String): Result<Pair<ForumsItem, List<RepliesItem>>>

    suspend fun postReply(token: String, request: PostReplyRequest): Result<PostReplyResponse>

}