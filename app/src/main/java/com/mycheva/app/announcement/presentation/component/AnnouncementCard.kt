package com.mycheva.app.announcement.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mycheva.app.announcement.data.Announcement
import kotlinx.serialization.json.JsonNull.content

@Composable
fun AnnouncementCard(
    modifier: Modifier = Modifier,
    announcement: Announcement,
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
                model = announcement.profileUrl,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = announcement.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = announcement.timeStamp,
                    color = Color.Gray
                )
            }
        }
        if (announcement.imageUrl.isNotBlank()) {
            AsyncImage(
                model = announcement.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            modifier = Modifier.fillMaxWidth(),
        )
        IconButton(onClick = { onAddBookMark() }) {
            if (announcement.isBookmarked) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = "bookmarked"
                )
            } else {
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = "add to boormark"
                )
            }
        }
    }
}

@Preview
@Composable
private fun AnnouncementCardPrev() {
    Surface {
        val data = Announcement(
            profileUrl = "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
            name = "Sendiko",
            timeStamp = "1 jam yang lalu",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ultrices posuere ante id vehicula. Maecenas sed augue fringilla, sodales lacus sed, tempor magna. Aliquam vestibulum tortor convallis nunc fermentum interdum. Duis eleifend, nibh sed egestas mollis, neque tellus efficitur massa, ",
            isBookmarked = true,
        )
        AnnouncementCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onAddBookMark = { },
            announcement = data
        )
    }
}