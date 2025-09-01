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
    companion object {
        fun from(announcement: Announcement): AnnouncementUi {
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

        fun to(announcementUi: AnnouncementUi): Announcement {
            return Announcement(
                id = announcementUi.id.toInt(),
                profileUrl = announcementUi.profileUrl,
                username = announcementUi.username,
                timeStamp = announcementUi.time,
                title = announcementUi.time,
                content = announcementUi.content,
                isBookmarked = announcementUi.isBookmarked,
                imageUrl = ""
            )
        }
    }
}
