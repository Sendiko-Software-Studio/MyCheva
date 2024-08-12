package com.mycheva.app.profile.edit_pass.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.profile.edit_pass.data.ChangePasswordRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditPasswordRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
) {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    fun changePassword(token: String, userId: String, request: ChangePasswordRequest) = apiServices.changePassword(token, userId, request)

}