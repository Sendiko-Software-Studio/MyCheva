package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName

data class Role(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("updatedAt")
    val updatedAt: String

)
