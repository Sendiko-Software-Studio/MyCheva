package com.mycheva.app.forum.main.data.dto

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.data.ForumItems

data class GetForumsResponse(

    @field:SerializedName("forums")
	val forumItems: List<ForumItems>,

    @field:SerializedName("message")
	val message: String,

    @field:SerializedName("status")
	val status: Int
)