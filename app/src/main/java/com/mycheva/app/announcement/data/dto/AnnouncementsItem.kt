package com.mycheva.app.announcement.data.dto

import com.mycheva.app.announcement.domain.Announcement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementsItem(

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("imageUrl")
    val imageUrl: String?,

    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("userId")
    val userId: Int,

    @SerialName("user")
    val user: User,

    @SerialName("content")
    val content: String,

    @SerialName("updatedAt")
    val updatedAt: String,
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
