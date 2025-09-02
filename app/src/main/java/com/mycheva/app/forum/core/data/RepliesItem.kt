package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.replies.domain.Replies

data class RepliesItem(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("forumId")
    val forumId: Int,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String,

    @field:SerializedName("user")
    val userItemWithRole: UserItemWithRole

)

fun RepliesItem.toDomain(): Replies {
    return Replies(
        id = id,
        userId = userId,
        forumId = forumId,
        content = content,
        user = userItemWithRole.toDomain()
    )
}

