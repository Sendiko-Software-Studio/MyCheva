package com.mycheva.app.forum.add.data.dto

import kotlinx.serialization.SerialName

data class ForumPostResponse(

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int
)
