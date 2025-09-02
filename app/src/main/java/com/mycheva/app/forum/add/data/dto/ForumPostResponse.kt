package com.mycheva.app.forum.add.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForumPostResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)
