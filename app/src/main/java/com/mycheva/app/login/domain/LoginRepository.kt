package com.mycheva.app.login.domain

interface LoginRepository {

    suspend fun login(username: String, password: String): Boolean
    fun verifyForm(username: String, password: String): Pair<Boolean, String?>

}