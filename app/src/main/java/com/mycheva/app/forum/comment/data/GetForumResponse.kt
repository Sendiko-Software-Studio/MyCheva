package com.mycheva.app.forum.comment.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.data.ForumsItem

data class GetForumResponse(

	@field:SerializedName("forum")
	val forum: ForumsItem,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)