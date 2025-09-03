package com.mycheva.app.forum.replies.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostReplyRequest(

    @SerialName("userId")
    val userId: Int,

    @SerialName("forumId")
    val forumId: Int,

    @SerialName("content")
    val content: String,
)
