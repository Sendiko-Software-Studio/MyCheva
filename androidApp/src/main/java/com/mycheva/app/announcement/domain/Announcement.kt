package com.mycheva.app.announcement.domain

data class Announcement(
    val id: Int,
    val profileUrl: String,
    val username: String,
    val timeStamp: String,
    val title: String,
    val content: String,
    val isBookmarked: Boolean = false,
    val imageUrl: String = "",
)