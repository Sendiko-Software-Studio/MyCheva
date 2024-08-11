package com.mycheva.app.login.data

import com.google.gson.annotations.SerializedName

data class LoginRequest(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String
)
