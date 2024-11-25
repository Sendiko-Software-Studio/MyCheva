package com.mycheva.app.meeting.detail.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.core.data.EventsItem

data class GetMeetingResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("event")
	val event: EventsItem,

	@field:SerializedName("status")
	val status: Int
)