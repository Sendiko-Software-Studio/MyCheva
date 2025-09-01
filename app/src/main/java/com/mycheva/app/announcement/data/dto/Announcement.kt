package com.mycheva.app.announcement.data.dto

data class Announcement(
    val profileUrl: String,
    val name: String,
    val timeStamp: String,
    val content: String,
    val isBookmarked: Boolean = false,
    val imageUrl: String = "",
)
