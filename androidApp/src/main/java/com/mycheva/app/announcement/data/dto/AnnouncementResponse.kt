package com.mycheva.app.announcement.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementResponse(

	@SerialName("message")
	val message: String,

	@SerialName("announcements")
	val announcements: List<AnnouncementsItem>,

	@SerialName("status")
	val status: Int
)
