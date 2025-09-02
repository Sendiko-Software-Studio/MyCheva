package com.mycheva.app.profile.main.data.dto.changepassword

import kotlinx.serialization.SerialName

data class ChangePasswordResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)