package com.mycheva.app.schedule.detail.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.schedule.core.EventsItem

data class GetEventResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("event")
	val event: EventsItem,

	@field:SerializedName("status")
	val status: Int
)