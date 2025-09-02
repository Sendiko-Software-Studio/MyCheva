package com.mycheva.app.attendance.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttendanceRequest(

	@SerialName("eventId")
	val eventId: String,

	@SerialName("userId")
	val userId: String,

	@SerialName("status")
	val status: String,
)
