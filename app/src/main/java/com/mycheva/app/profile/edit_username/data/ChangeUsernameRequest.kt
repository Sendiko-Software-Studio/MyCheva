package com.mycheva.app.profile.edit_username.data

import com.google.gson.annotations.SerializedName

data class ChangeUsernameRequest(

	@field:SerializedName("name")
	val name: String
)
