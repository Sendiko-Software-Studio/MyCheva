package com.mycheva.app.login.domain

import com.mycheva.app.core.preferences.AppPreferences
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val appPreferences: AppPreferences
) : LoginRepository {

    override suspend fun login(username: String, password: String): Boolean {
        appPreferences.saveToken("ABCD")
        return true
    }
}