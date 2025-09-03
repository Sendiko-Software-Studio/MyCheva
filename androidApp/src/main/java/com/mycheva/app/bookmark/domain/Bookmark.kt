package com.mycheva.app.bookmark.domain

data class Bookmark(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val title: String,
    val content: String,
)
