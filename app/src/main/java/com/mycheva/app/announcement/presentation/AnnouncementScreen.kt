package com.mycheva.app.announcement.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mycheva.app.R
import com.mycheva.app.announcement.presentation.component.AnnouncementCard
import com.mycheva.app.core.navigation.BookmarkScreen
import com.mycheva.app.core.ui.components.CardSkeleton
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.components.TextFieldType
import com.mycheva.app.core.ui.theme.poppinsFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AnnouncementScreen(
    state: AnnouncementState,
    onEvent: (AnnouncementEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
    animatedContentScope: AnimatedContentScope
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank()) {
            delay(300)
            onEvent(AnnouncementEvent.OnLoadAnnouncements(state.token))
        }
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.asString(context).isNotBlank()) {
            delay(2000)
            onEvent(AnnouncementEvent.OnClearState)
        }
    }


    NotificationBox(
        message = state.notificationMessage.asString(context),
        isLoading = false,
        isErrorNotification = state.isRequestError
    ) {
        Scaffold(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "announcement"),
                    animatedVisibilityScope = animatedContentScope
                ),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("Announcements", fontFamily = poppinsFamily)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { onNavigate(null) }
                        ) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { onNavigate(BookmarkScreen) }
                        ) {
                            Icon(Icons.Rounded.Bookmark, contentDescription = null)
                        }
                    }
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    contentPadding = paddingValues,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    item {
                        CustomTextField(
                            value = "",
                            onValueChange = {  },
                            onClearClick = {  },
                            type = TextFieldType.White,
                            modifier = Modifier
                                .fillMaxWidth().padding(horizontal = 16.dp),
                            label = "Cari..",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "cari"
                                )
                            }
                        )
                    }
                    item {
                        AnimatedVisibility(!state.isLoading && state.announcements.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    modifier = Modifier.size(256.dp),
                                    painter = painterResource(R.drawable.not_found),
                                    contentDescription = "Konten tidak ditemukan."
                                )
                            }
                        }
                    }
                    items(state.announcements) { announcement ->
                        AnnouncementCard(
                            announcement = AnnouncementUi.toAnnouncementUi(announcement),
                            onAddBookMark = {
                                onEvent(AnnouncementEvent.OnAddBookmark(announcement))
                            },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                    item {
                        (1..5).forEach { _ ->
                            AnimatedVisibility(
                                visible = state.isLoading && state.announcements.isEmpty(),
                                enter = expandVertically(),
                                exit = shrinkVertically()
                            ) {
                                CardSkeleton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}