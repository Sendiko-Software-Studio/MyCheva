package com.mycheva.app.forum.comment.presentation

import com.mycheva.app.forum.core.data.Replies

data class CommentUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val content: String,
    val role: String
) {
    companion object {
        fun toCommentUi(replies: Replies): CommentUi {
            return CommentUi(
                id = replies.id.toString(),
                profileUrl = if (replies.user.profileUrl.isNullOrBlank()) " " else replies.user.profileUrl,
                username = replies.user.name,
                time = replies.createdAt,
                content = replies.content,
                role = replies.user.role.name
            )
        }
    }
}

