package com.mycheva.app.announcement.data.dto

import com.google.gson.annotations.SerializedName
import com.mycheva.app.announcement.domain.Announcement


data class AnnouncementsItem(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("imageUrl")
    val imageUrl: String?,

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
) {
    fun to(announcementsItem: AnnouncementsItem): Announcement {
        return Announcement(
            id = announcementsItem.id,
            profileUrl = announcementsItem.user.profileUrl ?: "",
            username = announcementsItem.user.name,
            timeStamp = announcementsItem.createdAt,
            content = announcementsItem.content,
            title = announcementsItem.title,
            imageUrl = announcementsItem.imageUrl ?: ""
        )
    }
}