package com.mycheva.app.forum.main.presentation

import com.mycheva.app.forum.core.domain.Forum
import com.mycheva.app.forum.core.domain.Role
import com.mycheva.app.forum.core.domain.UserWithRole

data class ForumUi(
    val id: String,
    val profileUrl: String,
    val username: String,
    val time: String,
    val content: String,
    val comment: String,
    val role: String,
) {
    fun toForum(): Forum {
        return Forum(
            id = id,
            profileUrl = profileUrl,
            user = UserWithRole(
                name = username,
                profileUrl = profileUrl,
                role = Role(
                    id = 0,
                    name = role
                )
            ),
            time = time,
            content = content,
            comment = comment,
        )
    }
    companion object {
        fun fromForum(forum: Forum): ForumUi {
            return ForumUi(
                id = forum.id,
                profileUrl = forum.user.profileUrl,
                username = forum.user.name,
                time = forum.time,
                content = forum.content,
                comment = forum.comment,
                role = forum.user.role.name
            )
        }
    }
}
