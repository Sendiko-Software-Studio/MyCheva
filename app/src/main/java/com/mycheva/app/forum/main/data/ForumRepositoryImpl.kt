package com.mycheva.app.forum.main.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.core.data.toDomain
import com.mycheva.app.forum.core.domain.Forum
import com.mycheva.app.forum.main.domain.ForumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForumRepositoryImpl @Inject constructor(
    private val client: KtorClient,
    private val appPreferences: AppPreferences,
) : ForumRepository {

    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override suspend fun getForums(token: String): Result<List<Forum>, DataError.Remote> {
        val response = client.getForums(token)

        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.forumItems.map { it.toDomain() })
            }

            is Result.Error -> {
                Result.Error(response.error)
            }
        }
    }

}