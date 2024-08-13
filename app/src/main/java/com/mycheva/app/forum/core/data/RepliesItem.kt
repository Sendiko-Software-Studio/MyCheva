package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName

data class RepliesItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("forumId")
	val forumId: Int,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class User(

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
