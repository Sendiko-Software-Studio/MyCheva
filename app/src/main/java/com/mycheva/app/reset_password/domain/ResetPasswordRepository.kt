package com.mycheva.app.reset_password.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.reset_password.data.ResetPasswordRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetPasswordRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
) {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    fun resetPassword(token: String, request: ResetPasswordRequest) = apiServices.resetPassword(token, request)

}