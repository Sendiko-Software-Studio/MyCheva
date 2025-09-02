package com.mycheva.app.meeting.core.data

import com.mycheva.app.meeting.core.domain.Meeting
import kotlinx.serialization.SerialName

data class MeetingsItem(

    @SerialName("date")
    val date: String,

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("name")
    val name: String,

    @SerialName("details")
    val details: String,

    @SerialName("id")
    val id: Int,

    @SerialName("time")
    val time: String,

    @SerialName("divisionId")
    val divisionId: Int,

    @SerialName("type")
    val type: String,

    @SerialName("desc")
    val desc: String,

    @SerialName("updatedAt")
    val updatedAt: String,
)

fun MeetingsItem.toDomain(): Meeting {
    return Meeting(
        name = name,
        date = date,
        time = time,
        type = type,
        place = details,
        id = id.toString(),
        divisionId = divisionId
    )
}

