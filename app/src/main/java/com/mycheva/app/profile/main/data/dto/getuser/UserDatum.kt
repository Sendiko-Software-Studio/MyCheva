package com.mycheva.app.profile.main.data.dto.getuser

import com.google.gson.annotations.SerializedName
import com.mycheva.app.profile.main.domain.UserData

data class UserDatum(

    @field:SerializedName("nim")
    val nim: String,

    @field:SerializedName("major")
    val major: String,

    @field:SerializedName("imageUrl")
    val imageUrl: Any,

    @field:SerializedName("fullName")
    val fullName: String,

    @field:SerializedName("Division")
    val division: DivisionItem,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("divisionId")
    val divisionId: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("faculty")
    val faculty: String
)

fun UserDatum.toDomain(): UserData {
    return UserData(
        nim = nim,
        major = major,
        faculty = faculty,
        division = division.name,
        email = email,
        fullName = fullName
    )
}