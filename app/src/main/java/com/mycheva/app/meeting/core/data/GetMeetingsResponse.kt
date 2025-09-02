package com.mycheva.app.meeting.core.data

import com.google.gson.annotations.SerializedName

data class GetMeetingsResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("events")
	val events: List<MeetingsItem>,

	@field:SerializedName("status")
	val status: Int
)
