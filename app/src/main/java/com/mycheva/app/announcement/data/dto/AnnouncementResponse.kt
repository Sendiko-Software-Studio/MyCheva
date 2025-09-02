package com.mycheva.app.announcement.data.dto

import kotlinx.serialization.SerialName


data class AnnouncementResponse(

	@SerialName("message")
	val message: String,

	@SerialName("announcements")
	val announcements: List<AnnouncementsItem>,

	@SerialName("status")
	val status: Int
)
