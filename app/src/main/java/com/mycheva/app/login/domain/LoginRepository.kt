package com.mycheva.app.login.domain

interface LoginRepository {

    suspend fun login(username: String, password: String): Boolean

}