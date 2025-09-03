package com.mycheva.app.forum.replies.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.replies.data.dto.PostReplyRequest
import com.mycheva.app.forum.replies.domain.RepliesRepository
import com.mycheva.app.forum.replies.domain.ForumWithReplies
import com.mycheva.app.forum.core.data.toDomain
import kotlinx.coroutines.flow.Flow

class RepliesRepositoryImpl(
    private val client: KtorClient,
    private val appPreferences: AppPreferences
): RepliesRepository {

    override fun getToken(): Flow<String> = appPreferences.getToken()

    override fun getUserId(): Flow<String> = appPreferences.getUserId()
    override suspend fun loadData(
        token: String,
        forumId: String,
    ): Result<ForumWithReplies, DataError.Remote> {
        val response = client.getForum(token, forumId)

        return when (response) {
            is Result.Error -> Result.Error(response.error)
            is Result.Success -> Result.Success(
                ForumWithReplies(
                    forum = response.data.forumItems.toDomain(),
                    replies = response.data.forumItems.replies.map { it.toDomain() }
                )
            )
        }
    }

    override suspend fun postReply(
        token: String,
        userId: String,
        forumId: String,
        content: String,
    ): Result<String, DataError.Remote> {
        val request = PostReplyRequest(
            userId = userId.toInt(),
            forumId = forumId.toInt(),
            content = content
        )
        val response = client.postReply(token, request)

        return when (response) {
            is Result.Error -> Result.Error(response.error)
            is Result.Success -> Result.Success(response.data.message)
        }
    }

}