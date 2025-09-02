package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.domain.Forum

data class ForumItems(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("user")
    val userItemWithRole: UserItemWithRole,

    @field:SerializedName("Replies")
    val replies: List<RepliesItem>,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
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