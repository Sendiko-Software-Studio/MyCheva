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

    override fun verifyForm(username: String, password: String): Pair<Boolean, String?> {
        return when {
            username.isNotBlank() && password.isNotBlank() -> Pair(true, null)
            username.isBlank() -> Pair(false, "Username cannot be empty.")
            password.isBlank() -> Pair(false, "Password cannot be empty.")
            else -> Pair(false, "Username and password cannot be empty.")
        }
    }
}