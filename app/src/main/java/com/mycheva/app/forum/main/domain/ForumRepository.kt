package com.mycheva.app.forum.main.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.forum.core.data.ForumItems
import com.mycheva.app.forum.core.domain.Forum
import kotlinx.coroutines.flow.Flow

interface ForumRepository {

    fun getToken(): Flow<String>

    suspend fun getForums(token: String): Result<List<Forum>, DataError.Remote>

}