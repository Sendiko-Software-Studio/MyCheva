package com.mycheva.app.forum.core.data

import com.mycheva.app.forum.core.domain.Role
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoleItem(

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("name")
    val name: String,

    @SerialName("id")
    val id: Int,

    @SerialName("updatedAt")
    val updatedAt: String

)

fun RoleItem.toDomain(): Role {
    return Role(
        id = id,
        name = name
    )
}
