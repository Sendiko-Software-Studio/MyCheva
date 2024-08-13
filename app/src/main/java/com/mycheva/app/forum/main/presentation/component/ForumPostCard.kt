package com.mycheva.app.forum.main.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Comment
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
import com.mycheva.app.forum.core.data.ForumsItem

@Composable
fun ForumPostCard(
    modifier: Modifier = Modifier,
    forum: ForumsItem,
    onNavigate: (String) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = forum.user.profileUrl,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = forum.user.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = forum.createdAt.substring(0, 10),
                    color = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = forum.content,
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { onNavigate(forum.id.toString()) }) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.Comment, contentDescription = "comments")
            }
            Text(text = forum.replies.size.toString())
        }
    }
}