package com.mycheva.app.bookmark.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mycheva.app.announcement.presentation.component.AnnouncementCard
import com.mycheva.app.core.ui.components.CenteredAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkScreenState,
    onEvent: (BookmarkScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {
    Scaffold(
        topBar = {
            CenteredAppBar(
                title = "Bookmark",
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigationAction = { onNavigate(null) },
            )
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues
            ) {
                items(state.announcements) {
                    AnnouncementCard(
                        announcement = it,
                        onAddBookMark = {
                            onEvent(BookmarkScreenEvent.OnRemoveBookmark(it))
                        },
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun BookmarkScreenPrev() {
    BookmarkScreen(
        state = BookmarkScreenState(),
        onEvent = {  },
        onNavigate = {  }
    )
}