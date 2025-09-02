package com.mycheva.app.profile.main.data.dto.getuser

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: UserItem,

	@field:SerializedName("status")
	val status: Int
)
