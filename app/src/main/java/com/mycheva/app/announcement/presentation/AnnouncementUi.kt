package com.mycheva.app.announcement.presentation

import com.mycheva.app.announcement.domain.Announcement

data class AnnouncementUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val title: String,
    val content: String,
    val isBookmarked: Boolean = false,
) {

    fun toDomain(): Announcement {
        return Announcement(
            id = id.toInt(),
            profileUrl = profileUrl,
            username = username,
            timeStamp = time,
            title = time,
            content = content,
            isBookmarked = isBookmarked,
            imageUrl = ""
        )
    }
    companion object {
        fun fromDomain(announcement: Announcement): AnnouncementUi {
            return AnnouncementUi(
                id = announcement.id.toString(),
                profileUrl = announcement.profileUrl,
                username = announcement.username,
                time = announcement.timeStamp,
                content = announcement.content,
                title = announcement.title,
                isBookmarked = announcement.isBookmarked
            )
        }
    }
}
