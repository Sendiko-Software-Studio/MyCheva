package com.mycheva.app.forum.main.data

import com.google.gson.annotations.SerializedName
import com.mycheva.app.forum.core.data.Forum

data class GetForumsResponse(

	@field:SerializedName("forums")
	val forums: List<Forum>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

