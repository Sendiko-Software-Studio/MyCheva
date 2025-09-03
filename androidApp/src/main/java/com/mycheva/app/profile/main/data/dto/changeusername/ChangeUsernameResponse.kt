package com.mycheva.app.profile.main.data.dto.changeusername

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeUsernameResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)
