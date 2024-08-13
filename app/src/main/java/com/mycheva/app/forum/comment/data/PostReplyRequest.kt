package com.mycheva.app.forum.comment.data

import com.google.gson.annotations.SerializedName

data class PostReplyRequest(

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("forumId")
	val forumId: Int,

	@field:SerializedName("content")
	val content: String
)
