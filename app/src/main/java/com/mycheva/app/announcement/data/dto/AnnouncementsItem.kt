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
)

fun AnnouncementsItem.toDomain(): Announcement {
    return Announcement(
        id = this.id,
        profileUrl = this.user.profileUrl ?: "",
        username = this.user.name,
        timeStamp = this.createdAt,
        title = this.title,
        content = this.content,
        imageUrl = this.imageUrl ?: ""
    )
}
