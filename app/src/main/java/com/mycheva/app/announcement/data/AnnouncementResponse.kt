package com.mycheva.app.announcement.data

import com.google.gson.annotations.SerializedName

data class AnnouncementResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("announcements")
	val announcements: List<AnnouncementsItem>,

	@field:SerializedName("status")
	val status: Int
)

data class User(

	@field:SerializedName("profileUrl")
	val profileUrl: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class AnnouncementsItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
