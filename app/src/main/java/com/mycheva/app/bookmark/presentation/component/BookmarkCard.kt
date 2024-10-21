package com.mycheva.app.bookmark.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mycheva.app.bookmark.presentation.BookmarkUi
import com.mycheva.app.core.ui.components.timeAgo
import com.mycheva.app.core.ui.theme.poppinsFamily

@SuppressLint("NewApi")
@Composable
fun BookmarkCard(
    modifier: Modifier = Modifier,
    bookmark: BookmarkUi,
    onRemoveBookmark: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = bookmark.profileUrl,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = bookmark.username,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFamily
                )
                Text(
                    text = timeAgo(bookmark.time),
                    color = Color.Gray,
                    fontFamily = poppinsFamily
                )
            }
        }
        Text(
            text = bookmark.content,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = poppinsFamily
        )
        IconButton(onClick = { onRemoveBookmark() }) {
            Icon(
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = "add to boormark"
            )
        }
    }
}