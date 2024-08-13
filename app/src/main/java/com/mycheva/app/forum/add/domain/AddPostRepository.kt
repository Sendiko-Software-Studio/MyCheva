package com.mycheva.app.forum.add.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.forum.add.data.ForumPostRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddPostRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
) {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    fun postForum(token: String, request: ForumPostRequest) = apiServices.postForum(token, request)

}