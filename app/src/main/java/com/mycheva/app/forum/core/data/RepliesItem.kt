package com.mycheva.app.forum.core.data

import com.mycheva.app.forum.replies.domain.Replies
import kotlinx.serialization.SerialName

data class RepliesItem(

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("id")
    val id: Int,

    @SerialName("userId")
    val userId: Int,

    @SerialName("forumId")
    val forumId: Int,

    @SerialName("content")
    val content: String,

    @SerialName("updatedAt")
    val updatedAt: String,

    @SerialName("user")
    val userItemWithRole: UserItemWithRole,

    )

fun RepliesItem.toDomain(): Replies {
    return Replies(
        id = id,
        userId = userId,
        forumId = forumId,
        content = content,
        user = userItemWithRole.toDomain(),
        time = createdAt
    )
}

