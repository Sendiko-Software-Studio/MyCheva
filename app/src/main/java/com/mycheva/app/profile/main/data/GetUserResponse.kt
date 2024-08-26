package com.mycheva.app.profile.main.data

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("status")
	val status: Int
)

data class User(

	@field:SerializedName("profileUrl")
	val profileUrl: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("UserDatum")
	val userDatum: UserDatum
)

data class UserDatum(

	@field:SerializedName("nim")
	val nim: String,

	@field:SerializedName("major")
	val major: String,

	@field:SerializedName("imageUrl")
	val imageUrl: Any,

	@field:SerializedName("fullName")
	val fullName: String,

	@field:SerializedName("Division")
	val division: Division,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("divisionId")
	val divisionId: Int,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("faculty")
	val faculty: String
)

data class Division(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
