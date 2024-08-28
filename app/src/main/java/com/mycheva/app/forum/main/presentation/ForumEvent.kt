package com.mycheva.app.forum.main.presentation

sealed class ForumEvent {
    data object OnClearNotification: ForumEvent()
    data object OnClearFilter: ForumEvent()
    data class OnSearchTextChange(val value: String): ForumEvent()
    data class OnLoadForums(val token: String): ForumEvent()
}