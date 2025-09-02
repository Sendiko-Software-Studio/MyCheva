package com.mycheva.app.roadmap.data.dto

import com.google.gson.annotations.SerializedName
import com.mycheva.app.roadmap.domain.RoadMap

data class RoadmapsItem(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("divisionId")
    val divisionId: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("desc")
    val desc: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
)

fun RoadmapsItem.toDomain(): RoadMap {
    return RoadMap(
        divisionId = divisionId,
        title = title,
        desc = desc
    )
}
