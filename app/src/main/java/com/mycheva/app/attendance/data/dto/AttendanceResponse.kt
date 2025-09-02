package com.mycheva.app.attendance.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttendanceResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)
