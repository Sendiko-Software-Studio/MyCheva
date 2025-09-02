package com.mycheva.app.login.data.dto

import com.mycheva.app.login.domain.UserWithToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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

@Serializable
data class UserLoginItem(

	@SerialName("profileUrl")
	val profileUrl: String,

	@SerialName("password")
	val password: String,

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: Int,
)

fun LoginResponse.toDomain(): UserWithToken {
    return UserWithToken(
        id = userLoginItem.id,
        name = userLoginItem.name,
        profileUrl = userLoginItem.profileUrl,
        token = token
    )
}