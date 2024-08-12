package com.mycheva.app.dashboard.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.schedule.core.EventsItem

data class GetEventsResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("events")
	val events: List<EventsItem>,

	@field:SerializedName("status")
	val status: Int
)
