package com.mycheva.app.forum.core.data

import com.mycheva.app.forum.core.domain.Forum
import kotlinx.serialization.SerialName

data class ForumItems(

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("id")
    val id: Int,

    @SerialName("userId")
    val userId: Int,

    @SerialName("user")
    val userItemWithRole: UserItemWithRole,

    @SerialName("Replies")
    val replies: List<RepliesItem>,

    @SerialName("content")
    val content: String,

    @SerialName("updatedAt")
    val updatedAt: String,
)

fun ForumItems.toDomain(): Forum {
    return Forum(
        id = id.toString(),
        profileUrl = userItemWithRole.profileUrl,
        user = userItemWithRole.toDomain(),
        time = createdAt,
        content = content,
        comment = replies.size.toString()
    )
}