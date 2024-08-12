package com.mycheva.app.announcement.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.announcement.presentation.component.AnnouncementCard
import com.mycheva.app.core.navigation.BookmarkScreen
import com.mycheva.app.core.ui.components.CenteredAppBar
import com.mycheva.app.core.ui.components.NotificationBox
import kotlinx.coroutines.delay
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementScreen(
    state: AnnouncementScreenState,
    onEvent: (AnnouncementScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank()) {
            onEvent(AnnouncementScreenEvent.OnLoadAnnouncements(state.token))
        }
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()){
            delay(2000)
            onEvent(AnnouncementScreenEvent.OnClearState)
        }
    }


    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isRequestError
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
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    items(state.announcements) { announcement ->
                        AnnouncementCard(
                            announcement = announcement,
                            onAddBookMark = {
                                onEvent(AnnouncementScreenEvent.OnAddBookmark(announcement))
                            },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        )
    }
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