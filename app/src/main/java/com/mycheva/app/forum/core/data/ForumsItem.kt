package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.main.data.User

data class ForumsItem(

    @field:SerializedName("createdAt")
    val createdAt: String = "",

    @field:SerializedName("replies")
    val replies: List<RepliesItem> = emptyList(),

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("userId")
    val userId: Int = 0,

    @field:SerializedName("user")
    val user: User = User(),

    @field:SerializedName("content")
    val content: String = "",

    @field:SerializedName("updatedAt")
    val updatedAt: String = ""
)

