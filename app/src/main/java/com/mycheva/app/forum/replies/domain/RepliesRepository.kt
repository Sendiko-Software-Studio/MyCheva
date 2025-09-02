package com.mycheva.app.forum.replies.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import kotlinx.coroutines.flow.Flow

interface RepliesRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun loadData(
        token: String,
        forumId: String,
    ): Result<ForumWithReplies, DataError.Remote>

    suspend fun postReply(
        token: String,
        userId: String,
        forumId: String,
        content: String
    ): Result<String, DataError.Remote>

}