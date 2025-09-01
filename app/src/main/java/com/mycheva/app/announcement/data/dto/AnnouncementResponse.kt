package com.mycheva.app.announcement.data.dto

import com.google.gson.annotations.SerializedName

data class AnnouncementResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("announcements")
	val announcements: List<AnnouncementsItem>,

	@field:SerializedName("status")
	val status: Int
)
