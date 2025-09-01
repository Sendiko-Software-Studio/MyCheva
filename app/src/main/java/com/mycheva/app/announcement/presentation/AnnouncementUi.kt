package com.mycheva.app.announcement.presentation

import com.mycheva.app.announcement.data.dto.AnnouncementsItem

data class AnnouncementUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val content: String,
) {
    companion object {
        fun toAnnouncementUi(announcementsItem: AnnouncementsItem): AnnouncementUi {
            return AnnouncementUi(
                id = announcementsItem.id.toString(),
                profileUrl = announcementsItem.user.profileUrl.toString(),
                username = announcementsItem.user.name,
                time = announcementsItem.createdAt,
                content = announcementsItem.content
            )
        }
    }
}
