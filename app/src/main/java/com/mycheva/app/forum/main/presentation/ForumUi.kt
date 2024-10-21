package com.mycheva.app.forum.main.presentation

import com.mycheva.app.forum.core.data.ForumsItem

data class ForumUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val content: String,
    val comment: String,
) {
    companion object {
        fun toForumUi(forumsItem: ForumsItem): ForumUi {
            return ForumUi(
                id = forumsItem.id.toString(),
                profileUrl = forumsItem.user.profileUrl,
                username = forumsItem.user.name,
                time = forumsItem.createdAt,
                content = forumsItem.content,
                comment = forumsItem.replies.size.toString()
            )
        }
    }
}
