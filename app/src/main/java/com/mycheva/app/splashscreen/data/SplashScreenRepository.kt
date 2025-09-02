package com.mycheva.app.splashscreen.data

import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashScreenRepository @Inject constructor (
    private val appPreferences: AppPreferences,
) {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

}