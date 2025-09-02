package com.mycheva.app.roadmap.data.dto

import com.mycheva.app.roadmap.domain.RoadMap
import kotlinx.serialization.SerialName

data class RoadmapsItem(

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("id")
    val id: Int,

    @SerialName("divisionId")
    val divisionId: Int,

    @SerialName("title")
    val title: String,

    @SerialName("desc")
    val desc: String,

    @SerialName("updatedAt")
    val updatedAt: String,
)

fun RoadmapsItem.toDomain(): RoadMap {
    return RoadMap(
        divisionId = divisionId,
        title = title,
        desc = desc
    )
}
