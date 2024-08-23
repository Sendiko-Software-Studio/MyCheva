package com.mycheva.app.login.domain

import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse

interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>

    suspend fun saveToken(token: String)

    suspend fun saveUserId(userId: String)

}