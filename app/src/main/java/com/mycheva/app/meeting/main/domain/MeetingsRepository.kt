package com.mycheva.app.meeting.main.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MeetingsRepository @Inject constructor(
    private val apiService: ApiServices,
    private val appPreferences: AppPreferences
) {
    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getEvents(token: String) = apiService.getEvents(token)

}