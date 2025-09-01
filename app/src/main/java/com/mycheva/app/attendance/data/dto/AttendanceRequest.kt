package com.mycheva.app.attendance.data.dto

import com.google.gson.annotations.SerializedName

data class AttendanceRequest(

	@field:SerializedName("eventId")
	val eventId: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("status")
	val status: String
)
