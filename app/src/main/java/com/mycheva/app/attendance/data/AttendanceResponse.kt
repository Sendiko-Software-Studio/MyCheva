package com.mycheva.app.attendance.data

import com.google.gson.annotations.SerializedName

data class AttendanceResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
