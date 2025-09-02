package com.mycheva.app.profile.main.data.dto.getuser

import com.google.gson.annotations.SerializedName
import com.mycheva.app.profile.main.domain.User

data class UserItem(

    @field:SerializedName("profileUrl")
    val profileUrl: String?,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("UserDatum")
    val userDatum: UserDatum
)

fun UserItem.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        profileUrl = this.profileUrl ?: "",
        data = this.userDatum.toDomain()
    )
}
