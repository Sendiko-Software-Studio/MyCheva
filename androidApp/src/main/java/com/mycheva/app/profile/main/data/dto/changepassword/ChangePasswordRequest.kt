package com.mycheva.app.profile.main.data.dto.changepassword

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordRequest(

	@SerialName("password")
	val password: String,

	@SerialName("oldPassword")
	val oldPassword: String,
)