package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName

data class Forum(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("Replies")
    val replies: List<Replies>,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
)
