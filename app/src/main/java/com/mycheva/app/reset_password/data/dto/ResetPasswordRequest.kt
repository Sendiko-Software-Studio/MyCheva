package com.mycheva.app.reset_password.data.dto

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(

	@field:SerializedName("email")
	val email: String
)
