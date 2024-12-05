package com.mycheva.app.forum.main.presentation

import com.mycheva.app.forum.core.data.Forum

data class ForumUi(
    val id: String,
    val profileUrl: String?,
    val username: String,
    val time: String,
    val content: String,
    val comment: String,
    val role: String = "",
) {
    companion object {
        fun toForumUi(forum: Forum): ForumUi {
            return ForumUi(
                id = forum.id.toString(),
                profileUrl = if (forum.user.profileUrl.isNullOrBlank()) " " else forum.user.profileUrl,
                username = forum.user.name,
                time = forum.createdAt,
                content = forum.content,
                comment = forum.replies.size.toString()
            )
        }
    }
}
