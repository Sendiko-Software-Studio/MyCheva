package com.mycheva.app.profile.main.data.dto.changeusername

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeUsernameRequest(

	@SerialName("username")
	val name: String,
)
