package com.mycheva.app.reset_password.data.dto

import kotlinx.serialization.SerialName

data class ResetPasswordRequest(

	@SerialName("email")
	val email: String,
)
