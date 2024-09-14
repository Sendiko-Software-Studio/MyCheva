package com.mycheva.app.profile.main.data

import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
