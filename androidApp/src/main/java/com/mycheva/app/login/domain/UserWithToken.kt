package com.mycheva.app.login.domain

data class UserWithToken(
    val id: Int,
    val name: String,
    val profileUrl: String,
    val token: String,
)
