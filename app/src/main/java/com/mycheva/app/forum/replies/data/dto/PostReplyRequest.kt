package com.mycheva.app.forum.replies.data.dto

import kotlinx.serialization.SerialName

data class PostReplyRequest(

    @SerialName("userId")
    val userId: Int,

    @SerialName("forumId")
    val forumId: Int,

    @SerialName("content")
    val content: String,
)
