package com.mycheva.app.profile.edit_username.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.profile.edit_username.data.ChangeUsernameRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditUsernameRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
)  {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    fun changeUsername(token: String, userId: String, request: ChangeUsernameRequest) = apiServices.changeUsername(token, userId, request)

}