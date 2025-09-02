package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.domain.Role

data class RoleItem(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("updatedAt")
    val updatedAt: String

)

fun RoleItem.toDomain(): Role {
    return Role(
        id = id,
        name = name
    )
}
