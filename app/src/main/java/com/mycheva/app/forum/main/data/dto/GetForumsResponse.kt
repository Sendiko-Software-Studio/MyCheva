package com.mycheva.app.forum.main.data.dto

import com.mycheva.app.forum.core.data.ForumItems
import kotlinx.serialization.SerialName

data class GetForumsResponse(

    @SerialName("forums")
    val forumItems: List<ForumItems>,

    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int,
)