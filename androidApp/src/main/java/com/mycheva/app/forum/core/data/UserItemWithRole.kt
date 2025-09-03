package com.mycheva.app.forum.core.data

import com.mycheva.app.forum.core.domain.UserWithRole
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItemWithRole(

    @SerialName("profileUrl")
    val profileUrl: String,

    @SerialName("password")
    val password: String,

    @SerialName("name")
    val name: String,

    @SerialName("id")
    val id: Int,

    @SerialName("Role")
    val roleItem: RoleItem
)

fun UserItemWithRole.toDomain(): UserWithRole {
    return UserWithRole(
        name = name,
        profileUrl = profileUrl,
        role = roleItem.toDomain()
    )
}
