package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("profileUrl")
    val profileUrl: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("role")
    val role: Role
)
