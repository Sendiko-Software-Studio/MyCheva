package com.mycheva.app.announcement.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mycheva.app.announcement.presentation.component.AnnouncementCard
import com.mycheva.app.core.navigation.BookmarkScreen
import com.mycheva.app.core.ui.components.CenteredAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementScreen(
    state: AnnouncementScreenState,
    onEvent: (AnnouncementScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {
    Scaffold(
        topBar = {
            CenteredAppBar(
                title = "Announcement",
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigationAction = { onNavigate(null) },
                actionIcon = Icons.Rounded.Bookmark,
                actionAction = {
                    onNavigate(BookmarkScreen)
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues
            ) {
                items(state.announcements) { announcement ->
                    AnnouncementCard(
                        announcement = announcement,
                        onAddBookMark = {
                            onEvent(AnnouncementScreenEvent.OnAddBookmark(announcement))
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun AnnouncementScreenPrev() {
    AnnouncementScreen(
        state = AnnouncementScreenState(),
        onEvent = {  },
        onNavigate = {  }
    )
}