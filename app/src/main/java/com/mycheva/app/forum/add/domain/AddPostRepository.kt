package com.mycheva.app.forum.add.domain

import com.mycheva.app.forum.add.data.ForumPostRequest
import com.mycheva.app.forum.add.data.ForumPostResponse
import kotlinx.coroutines.flow.Flow

interface AddPostRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun postForum(token: String, request: ForumPostRequest): Result<ForumPostResponse>

}