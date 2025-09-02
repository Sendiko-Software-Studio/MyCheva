package com.mycheva.app.reset_password.data.dto

import kotlinx.serialization.SerialName

data class ResetPasswordResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)
