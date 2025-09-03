package com.mycheva.app.forum.add.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.add.data.dto.ForumPostRequest
import com.mycheva.app.forum.add.domain.AddPostRepository
import kotlinx.coroutines.flow.Flow

class AddPostRepositoryImpl(
    private val client: KtorClient,
    private val appPreferences: AppPreferences
) : AddPostRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun postForum(
        token: String,
        userId: String,
        content: String,
    ): Result<String, DataError.Remote> {
        val request = ForumPostRequest(userId.toInt(), content)
        val response = client.postForum(token, request)

        return when (response) {
            is Result.Success -> Result.Success(response.data.message)
            is Result.Error -> Result.Error(response.error)
        }
    }

}