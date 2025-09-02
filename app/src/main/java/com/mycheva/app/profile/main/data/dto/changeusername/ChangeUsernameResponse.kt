package com.mycheva.app.profile.main.data.dto.changeusername

import com.google.gson.annotations.SerializedName

data class ChangeUsernameResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
