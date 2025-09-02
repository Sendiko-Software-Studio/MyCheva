package com.mycheva.app.forum.add.data.dto

import com.google.gson.annotations.SerializedName

data class ForumPostRequest(

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("content")
	val content: String? = null
)
