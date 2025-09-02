package com.mycheva.app.attendance.data.dto

import kotlinx.serialization.SerialName

data class AttendanceResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)
