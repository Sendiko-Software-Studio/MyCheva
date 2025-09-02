package com.mycheva.app.profile.main.data.dto.getuser

import kotlinx.serialization.SerialName

data class GetUserResponse(

	@SerialName("message")
	val message: String,

	@SerialName("user")
	val user: UserItem,

	@SerialName("status")
	val status: Int,
)
