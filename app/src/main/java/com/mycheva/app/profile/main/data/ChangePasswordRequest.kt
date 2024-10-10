package com.mycheva.app.profile.main.data

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("oldPassword")
	val oldPassword: String
)
