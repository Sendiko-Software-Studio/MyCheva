package com.mycheva.app.profile.main.data.dto.changepassword

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)