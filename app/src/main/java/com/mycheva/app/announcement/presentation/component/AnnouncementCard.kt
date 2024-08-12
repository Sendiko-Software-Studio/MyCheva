package com.mycheva.app.announcement.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.mycheva.app.announcement.data.AnnouncementsItem

@Composable
fun AnnouncementCard(
    modifier: Modifier = Modifier,
    announcement: AnnouncementsItem,
    onAddBookMark: () -> Unit,
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
                model = announcement.user.profileUrl,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = announcement.user.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = announcement.createdAt,
                    color = Color.Gray
                )
            }
        }
        if (announcement.imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = announcement.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = announcement.content,
            modifier = Modifier.fillMaxWidth(),
        )
        IconButton(onClick = { onAddBookMark() }) {
            Icon(
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = "add to boormark"
            )
        }
    }
}
