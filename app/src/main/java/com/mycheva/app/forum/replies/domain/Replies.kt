package com.mycheva.app.forum.replies.domain

import com.mycheva.app.forum.core.domain.UserWithRole

data class Replies(
    val id: Int,
    val userId: Int,
    val forumId: Int,
    val content: String,
    val user: UserWithRole,
    val time: String,
)