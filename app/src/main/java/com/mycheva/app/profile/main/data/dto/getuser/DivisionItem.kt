package com.mycheva.app.profile.main.data.dto.getuser

import com.google.gson.annotations.SerializedName
import com.mycheva.app.profile.main.domain.Division

data class DivisionItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

fun DivisionItem.toDomain(): Division {
    return Division(
        name = name,
        id = id
    )
}