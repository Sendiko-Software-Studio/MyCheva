package com.mycheva.app.profile.main.data.dto.changeusername

import kotlinx.serialization.SerialName

data class ChangeUsernameRequest(

	@SerialName("username")
	val name: String,
)
