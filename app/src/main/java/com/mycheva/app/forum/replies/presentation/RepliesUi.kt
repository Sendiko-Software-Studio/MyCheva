package com.mycheva.app.forum.replies.presentation

import com.mycheva.app.forum.replies.domain.Replies

data class RepliesUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val content: String,
    val role: String,
) {
    companion object {
        fun fromReply(replies: Replies): RepliesUi {
            return RepliesUi(
                id = replies.id.toString(),
                profileUrl = replies.user.profileUrl,
                username = replies.user.name,
                time = replies.time,
                content = replies.content,
                role = replies.user.role.name
            )
        }
    }
}

