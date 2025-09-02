package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.domain.UserWithRole

data class UserItemWithRole(

    @field:SerializedName("profileUrl")
    val profileUrl: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("Role")
    val roleItem: RoleItem
)

fun UserItemWithRole.toDomain(): UserWithRole {
    return UserWithRole(
        name = name,
        profileUrl = profileUrl,
        role = roleItem.toDomain()
    )
}
