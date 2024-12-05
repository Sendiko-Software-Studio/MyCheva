package com.mycheva.app.forum.comment.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.data.Forum

data class GetForumResponse(

    @field:SerializedName("forum")
	val forum: Forum,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Int
)