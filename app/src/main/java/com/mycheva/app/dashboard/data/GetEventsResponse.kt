package com.mycheva.app.dashboard.data

import com.google.gson.annotations.SerializedName

data class GetEventsResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("events")
	val events: List<EventsItem>,

	@field:SerializedName("status")
	val status: Int
)

data class EventsItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("details")
	val details: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("divisionId")
	val divisionId: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
