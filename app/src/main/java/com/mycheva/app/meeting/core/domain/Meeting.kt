package com.mycheva.app.meeting.core.domain

data class Meeting(
    val id: String,
    val name: String,
    val date: String,
    val time: String,
    val type: String,
    val place: String,
    val divisionId: Int
)