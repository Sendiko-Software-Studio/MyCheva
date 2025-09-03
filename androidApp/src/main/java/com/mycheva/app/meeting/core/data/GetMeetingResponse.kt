package com.mycheva.app.meeting.core.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMeetingResponse(

    @SerialName("message")
    val message: String,

    @SerialName("event")
    val event: MeetingsItem,

    @SerialName("status")
    val status: Int,
)