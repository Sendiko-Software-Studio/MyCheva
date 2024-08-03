package com.mycheva.app.announcement

data class Announcement(
    val profileUrl: String,
    val name: String,
    val timeStamp: String,
    val content: String,
    val isBookmarked: Boolean = false,
    val imageUrl: String = "",
)
