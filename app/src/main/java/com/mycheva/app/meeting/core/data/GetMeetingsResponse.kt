package com.mycheva.app.meeting.core.data

import kotlinx.serialization.SerialName

data class GetMeetingsResponse(

	@SerialName("message")
	val message: String,

	@SerialName("events")
	val events: List<MeetingsItem>,

	@SerialName("status")
	val status: Int,
)
