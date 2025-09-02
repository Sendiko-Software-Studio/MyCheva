package com.mycheva.app.login.data.dto

import com.google.gson.annotations.SerializedName
import com.mycheva.app.login.domain.UserWithToken

data class LoginResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val userLoginItem: UserLoginItem,

	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("token")
	val token: String
)

data class UserLoginItem(

	@field:SerializedName("profileUrl")
	val profileUrl: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

fun LoginResponse.toDomain(): UserWithToken {
	return UserWithToken(
        id = userLoginItem.id,
        name = userLoginItem.name,
        profileUrl = userLoginItem.profileUrl,
		token = token
    )
}