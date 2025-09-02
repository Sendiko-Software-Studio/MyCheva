package com.mycheva.app.login.data.dto

import com.mycheva.app.login.domain.UserWithToken
import kotlinx.serialization.SerialName

data class LoginResponse(

	@SerialName("message")
	val message: String,

	@SerialName("user")
	val userLoginItem: UserLoginItem,

	@SerialName("status")
	val status: Int,

	@SerialName("token")
	val token: String,
)

data class UserLoginItem(

	@SerialName("profileUrl")
	val profileUrl: String,

	@SerialName("createdAt")
	val createdAt: String,

	@SerialName("password")
	val password: String,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: Int,

	@SerialName("updatedAt")
	val updatedAt: String,
)

fun LoginResponse.toDomain(): UserWithToken {
    return UserWithToken(
        id = userLoginItem.id,
        name = userLoginItem.name,
        profileUrl = userLoginItem.profileUrl,
        token = token
    )
}