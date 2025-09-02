package com.mycheva.app.forum.replies.data.dto

import kotlinx.serialization.SerialName

data class PostReplyResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)
