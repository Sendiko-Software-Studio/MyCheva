package com.mycheva.app.forum.core.domain

data class UserWithRole(
    val name: String,
    val profileUrl: String,
    val role: Role
)
