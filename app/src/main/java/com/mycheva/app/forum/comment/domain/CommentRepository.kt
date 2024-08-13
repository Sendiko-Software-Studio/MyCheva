package com.mycheva.app.forum.comment.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
) {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    fun loadData(token: String, forumId: String) = apiServices.getForum(token, forumId)

}