package com.mycheva.app.bookmark.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mycheva.app.announcement.presentation.component.AnnouncementCard
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkScreenState,
    onEvent: (BookmarkScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Bookmark")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary500,
                    navigationIconContentColor = Primary50,
                    actionIconContentColor = Primary50,
                    titleContentColor = Primary50
                ),
                navigationIcon = {
                    IconButton(onClick = { onNavigate(null) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "arrow back"
                        )
                    }
                },
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