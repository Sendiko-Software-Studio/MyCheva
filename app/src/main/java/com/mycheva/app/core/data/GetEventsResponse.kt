package com.mycheva.app.core.data

import com.google.gson.annotations.SerializedName

data class GetEventsResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("events")
	val events: List<EventsItem>,

	@field:SerializedName("status")
	val status: Int
)
