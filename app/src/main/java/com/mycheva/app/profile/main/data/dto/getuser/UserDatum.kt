package com.mycheva.app.profile.main.data.dto.getuser

import com.mycheva.app.profile.main.domain.UserData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDatum(

    @SerialName("nim")
    val nim: String,

    @SerialName("major")
    val major: String,

    @SerialName("imageUrl")
    val imageUrl: String,

    @SerialName("fullName")
    val fullName: String,

    @SerialName("Division")
    val division: DivisionItem,

    @SerialName("id")
    val id: Int,

    @SerialName("divisionId")
    val divisionId: Int,

    @SerialName("userId")
    val userId: Int,

    @SerialName("email")
    val email: String,

    @SerialName("faculty")
    val faculty: String,
)

fun UserDatum.toDomain(): UserData {
    return UserData(
        nim = nim,
        major = major,
        faculty = faculty,
        division = division.toDomain(),
        email = email,
        fullName = fullName
    )
}