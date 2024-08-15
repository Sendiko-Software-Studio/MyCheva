package com.mycheva.app.forum.add.data

import com.google.gson.annotations.SerializedName

data class ForumPostResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
