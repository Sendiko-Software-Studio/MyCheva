package com.mycheva.app.profile.main.data

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: User1,

	@field:SerializedName("status")
	val status: Int
)

data class Division(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class UserDatum(

	@field:SerializedName("division")
	val division: Division,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("nim")
	val nim: String,

	@field:SerializedName("major")
	val major: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("fullName")
	val fullName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("divisionId")
	val divisionId: Int,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("faculty")
	val faculty: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class User1(

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

	@field:SerializedName("user_datum")
	val userDatum: UserDatum,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
