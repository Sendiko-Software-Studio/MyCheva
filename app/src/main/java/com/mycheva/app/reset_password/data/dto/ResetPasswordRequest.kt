package com.mycheva.app.reset_password.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequest(

	@SerialName("email")
	val email: String,
)
