package com.mycheva.app.bookmark.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.components.LargeTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    onEvent: (BookmarkEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {
    Scaffold(
        topBar = {
            LargeTopBar(
                title = "Bookmark",
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigationAction = { onNavigate(null) },
            )
        },
        content = { paddingValues ->
            val contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp
            )
            LazyColumn(
                contentPadding = contentPadding
            ) {
                items(state.bookmarks) {
                    BookmarkCard(
                        bookmark = it,
                        onRemoveBookmark = {
                            onEvent(BookmarkEvent.OnRemoveBookmark(it))
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
        state = BookmarkState(),
        onEvent = {  },
        onNavigate = {  }
    )
}