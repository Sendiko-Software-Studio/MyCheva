package com.mycheva.app.schedule.main.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleScreenRepository @Inject constructor(
    private val apiService: ApiServices,
    private val appPreferences: AppPreferences
) {
    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getEvents(token: String) = apiService.getEvents(token)

}