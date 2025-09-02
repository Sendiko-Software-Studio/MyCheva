package com.mycheva.app.profile.main.data.dto.changepassword

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("oldPassword")
	val oldPassword: String
)