package com.mycheva.app.forum.main.presentation

sealed class ForumEvent {
    data object OnClearNotification: ForumEvent()
    data class OnLoadForums(val token: String): ForumEvent()
}