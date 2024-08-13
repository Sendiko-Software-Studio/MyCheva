package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.main.data.RepliesItem
import com.mycheva.app.forum.main.data.User

data class ForumsItem(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("replies")
    val replies: List<RepliesItem>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
)

