package com.mycheva.app.profile.main.data.dto

import kotlinx.serialization.SerialName

data class LogoutResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Boolean,
)
