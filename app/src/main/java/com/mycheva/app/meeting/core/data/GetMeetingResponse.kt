package com.mycheva.app.meeting.core.data

import com.google.gson.annotations.SerializedName

data class GetMeetingResponse(

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("event")
	val event: MeetingsItem,

    @field:SerializedName("status")
	val status: Int
)