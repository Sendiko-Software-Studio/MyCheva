package com.mycheva.app.forum.comment.presentation

import com.mycheva.app.forum.core.data.RepliesItem

data class CommentUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val content: String,
) {
    companion object {
        fun toCommentUi(repliesItem: RepliesItem): CommentUi {
            return CommentUi(
                id = repliesItem.id.toString(),
                profileUrl = repliesItem.user.profileUrl,
                username = repliesItem.user.name,
                time = repliesItem.createdAt,
                content = repliesItem.content
            )
        }
    }
}

