package com.mycheva.app.profile.main.domain

data class User(
    val id: Int,
    val name: String,
    val profileUrl: String,
    val data: UserData
)

data class UserData(
    val fullName: String,
    val nim: String,
    val email: String,
    val major: String,
    val faculty: String,
    val division: Division
)