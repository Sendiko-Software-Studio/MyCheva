package com.mycheva.app.forum.core.domain

data class Forum(
    val id: String,
    val profileUrl: String,
    val user: UserWithRole,
    val time: String,
    val content: String,
    val comment: String,
)
