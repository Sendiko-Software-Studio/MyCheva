package com.mycheva.app.forum.replies.domain

import com.mycheva.app.forum.core.domain.Forum

data class ForumWithReplies(
    val forum: Forum,
    val replies: List<Replies>
)
