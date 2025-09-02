package com.mycheva.app.forum.add.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForumPostRequest(

	@SerialName("userId")
	val userId: Int? = null,

	@SerialName("content")
	val content: String? = null
)
