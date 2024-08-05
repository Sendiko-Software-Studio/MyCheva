package com.mycheva.app.profile.main.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val appPreferences: AppPreferences,
    private val apiServices: ApiServices
) {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    suspend fun deleteToken() = appPreferences.deleteToken()

    fun getUser(token: String) = apiServices.getUser(token)

    fun logout(token: String) = apiServices.logout(token)

}