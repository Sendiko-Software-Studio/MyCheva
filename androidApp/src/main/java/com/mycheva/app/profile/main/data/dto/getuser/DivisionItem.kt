package com.mycheva.app.profile.main.data.dto.getuser

import com.mycheva.app.profile.main.domain.Division
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DivisionItem(

    @SerialName("name")
    val name: String,

    @SerialName("id")
    val id: Int,
)

fun DivisionItem.toDomain(): Division {
    return Division(
        name = name,
        id = id
    )
}