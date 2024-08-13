package com.mycheva.app.forum.add.presentation

sealed class AddPostScreenEvent {
    data object OnClearState: AddPostScreenEvent()
    data class OnPostTextChanged(val text: String): AddPostScreenEvent()
    data class OnPost(val token: String, val userId: String, val content: String): AddPostScreenEvent()
}