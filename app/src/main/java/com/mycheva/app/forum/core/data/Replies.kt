package com.mycheva.app.forum.core.data

import com.google.gson.annotations.SerializedName

data class Replies(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("forumId")
	val forumId: Int,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String,

	@field:SerializedName("user")
	val user: User,

)

