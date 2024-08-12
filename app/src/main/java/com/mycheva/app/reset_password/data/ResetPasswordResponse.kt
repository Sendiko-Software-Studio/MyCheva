package com.mycheva.app.reset_password.data

import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
