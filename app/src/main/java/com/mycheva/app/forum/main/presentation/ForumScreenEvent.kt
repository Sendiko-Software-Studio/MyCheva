package com.mycheva.app.forum.main.presentation

sealed class ForumScreenEvent {
    data object OnClearNotification: ForumScreenEvent()
}