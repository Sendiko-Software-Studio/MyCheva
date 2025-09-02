package com.mycheva.app.forum.replies.data.dto

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.data.ForumItems

data class GetForumResponse(

    @field:SerializedName("forum")
	val forumItems: ForumItems,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Int
)