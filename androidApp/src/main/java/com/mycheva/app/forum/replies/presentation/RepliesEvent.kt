package com.mycheva.app.forum.replies.presentation

sealed class RepliesEvent {
    data class OnRepliesTextChange(val value: String) : RepliesEvent()
    data object OnClearState: RepliesEvent()
    data class OnLoadData(val token: String, val forumId: String): RepliesEvent()
    data class OnPostReplies(val token: String, val userId: String, val forumId: String): RepliesEvent()
}