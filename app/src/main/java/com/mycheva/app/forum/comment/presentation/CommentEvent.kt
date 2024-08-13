package com.mycheva.app.forum.comment.presentation

sealed class CommentEvent {
    data class OnCommentTextChange(val value: String) : CommentEvent()
    data object OnClearState: CommentEvent()
    data class OnLoadData(val token: String, val forumId: String): CommentEvent()
    data class OnPostComment(val token: String, val userId: String, val forumId: String): CommentEvent()
}