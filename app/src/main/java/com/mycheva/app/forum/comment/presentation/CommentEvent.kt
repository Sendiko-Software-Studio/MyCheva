package com.mycheva.app.forum.comment.presentation

sealed class CommentEvent {
    data class OnCommentTextChange(val value: String) : CommentEvent()
    data object OnClearState: CommentEvent()
}