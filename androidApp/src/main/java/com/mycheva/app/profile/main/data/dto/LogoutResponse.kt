package com.mycheva.app.profile.main.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Boolean,
)
