package com.mycheva.app.forum.comment.data

import com.google.gson.annotations.SerializedName

data class PostReplyResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
