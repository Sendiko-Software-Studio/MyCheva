package com.mycheva.app.announcement.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.mycheva.app.announcement.presentation.component.AnnouncementCard
import com.mycheva.app.core.navigation.BookmarkScreen
import com.mycheva.app.core.ui.components.CenteredAppBar
import com.mycheva.app.core.ui.components.NotificationBox
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AnnouncementScreen(
    state: AnnouncementScreenState,
    onEvent: (AnnouncementScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
    animatedContentScope: AnimatedContentScope
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank()) {
            delay(300)
            onEvent(AnnouncementScreenEvent.OnLoadAnnouncements(state.token))
        }
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
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
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "announcement"),
                    animatedVisibilityScope = animatedContentScope
                ),
            topBar = {
                CenteredAppBar(
                    title = "Announcement",
                    navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                    navigationAction = { onNavigate(null) },
                    actionIcon = Icons.Rounded.Bookmark,
                    actionAction = {
                        onNavigate(BookmarkScreen)
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    contentPadding = paddingValues,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
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