package com.mycheva.app.forum.add.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.forum.add.data.dto.ForumPostRequest
import com.mycheva.app.forum.add.data.dto.ForumPostResponse
import kotlinx.coroutines.flow.Flow

interface AddPostRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun postForum(token: String, userId: String, content: String): Result<String, DataError.Remote>

}