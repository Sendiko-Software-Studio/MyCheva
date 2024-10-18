package com.mycheva.app.forum.add.presentation

sealed class AddPostForumEvent {
    data object OnClearState: AddPostForumEvent()
    data class OnPostTextChanged(val text: String): AddPostForumEvent()
    data class OnPost(val token: String, val userId: String, val content: String): AddPostForumEvent()
}