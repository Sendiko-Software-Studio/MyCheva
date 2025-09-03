package com.mycheva.app.bookmark.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.bookmark.presentation.component.BookmarkCard
import com.mycheva.app.core.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    onEvent: (BookmarkEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Bookmark", fontFamily = poppinsFamily)
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigate(null) }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "back")
                    }
                }
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
                            onEvent(BookmarkEvent.OnRemoveBookmark(it.toDomain()))
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