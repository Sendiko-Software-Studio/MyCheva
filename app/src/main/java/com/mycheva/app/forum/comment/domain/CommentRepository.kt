package com.mycheva.app.forum.comment.domain

import com.mycheva.app.forum.comment.data.GetForumResponse
import com.mycheva.app.forum.comment.data.PostReplyRequest
import com.mycheva.app.forum.comment.data.PostReplyResponse
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun loadData(token: String, forumId: String): Result<GetForumResponse>

    suspend fun postReply(token: String, request: PostReplyRequest): Result<PostReplyResponse>

}