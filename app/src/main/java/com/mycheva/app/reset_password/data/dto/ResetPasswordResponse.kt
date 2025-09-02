package com.mycheva.app.reset_password.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)
