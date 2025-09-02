package com.mycheva.app.profile.main.data.dto.changeusername

import com.google.gson.annotations.SerializedName

data class ChangeUsernameRequest(

	@field:SerializedName("username")
	val name: String
)
