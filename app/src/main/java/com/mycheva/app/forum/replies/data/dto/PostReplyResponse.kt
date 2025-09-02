package com.mycheva.app.forum.replies.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostReplyResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)
