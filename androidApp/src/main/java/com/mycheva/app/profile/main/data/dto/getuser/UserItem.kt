package com.mycheva.app.profile.main.data.dto.getuser

import com.mycheva.app.profile.main.domain.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItem(

    @SerialName("profileUrl")
    val profileUrl: String?,

    @SerialName("password")
    val password: String,

    @SerialName("name")
    val name: String,

    @SerialName("id")
    val id: Int,

    @SerialName("UserDatum")
    val userDatum: UserDatum,
)

fun UserItem.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        profileUrl = this.profileUrl ?: "",
        data = this.userDatum.toDomain()
    )
}
