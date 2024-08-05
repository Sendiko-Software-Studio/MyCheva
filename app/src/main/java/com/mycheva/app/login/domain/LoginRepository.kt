package com.mycheva.app.login.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.login.data.LoginRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val appPreferences: AppPreferences,
    private val apiServices: ApiServices
) {

    fun login(loginRequest: LoginRequest) = apiServices.login(loginRequest)

    suspend fun saveToken(token: String) {
        appPreferences.saveToken(token)
    }

}