package com.mycheva.app.announcement.domain

import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnnouncementRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
) {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getAnnouncements(token: String) =  apiServices.getAnnouncements(token)

}