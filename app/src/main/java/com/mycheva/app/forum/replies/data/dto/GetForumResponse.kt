package com.mycheva.app.forum.replies.data.dto

import com.mycheva.app.forum.core.data.ForumItems
import kotlinx.serialization.SerialName

data class GetForumResponse(

    @SerialName("forum")
    val forumItems: ForumItems,

    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int,
)