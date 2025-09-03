package com.mycheva.app.login.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result

interface LoginRepository {

    suspend fun login(
        username: String,
        password: String,
    ): Result<UserWithToken, DataError.Remote>

    suspend fun saveToken(token: String)

    suspend fun saveUserId(userId: String)

}