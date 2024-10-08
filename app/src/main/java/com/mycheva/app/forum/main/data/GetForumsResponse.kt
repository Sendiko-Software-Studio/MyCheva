package com.mycheva.app.forum.main.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.data.ForumsItem

data class GetForumsResponse(

	@field:SerializedName("forums")
	val forums: List<ForumsItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class User(

	@field:SerializedName("profileUrl")
	val profileUrl: String = "",

	@field:SerializedName("createdAt")
	val createdAt: String = "",

	@field:SerializedName("password")
	val password: String = "",

	@field:SerializedName("name")
	val name: String = "",

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("updatedAt")
	val updatedAt: String = ""
)