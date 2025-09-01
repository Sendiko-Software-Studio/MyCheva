package com.mycheva.app.announcement.data.dto

import com.google.gson.annotations.SerializedName


data class User(

    @field:SerializedName("profileUrl")
    val profileUrl: String?,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("roleId")
    val roleId: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
