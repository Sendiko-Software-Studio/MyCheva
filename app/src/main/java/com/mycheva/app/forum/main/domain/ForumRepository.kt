package com.mycheva.app.forum.main.domain

import com.mycheva.app.forum.core.data.Forum
import kotlinx.coroutines.flow.Flow

interface ForumRepository {

    fun getToken(): Flow<String>

    suspend fun getForums(token: String): Result<List<Forum>>

}