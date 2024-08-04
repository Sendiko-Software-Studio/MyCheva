package com.mycheva.app.forum.main.presentation.component

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.automirrored.rounded.Comment
import androidx.compose.material.icons.rounded.Comment
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
import com.mycheva.app.forum.main.data.Forum
import kotlinx.serialization.json.JsonNull.content

@Composable
fun ForumPostCard(
    modifier: Modifier = Modifier,
    forum: Forum
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = forum.profileUrl,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = forum.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = forum.timeStamp,
                    color = Color.Gray
                )
            }
        }
        if (forum.imageUrl.isNotBlank()) {
            AsyncImage(
                model = forum.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.Comment, contentDescription = "comments")
            }
            Text(text = forum.totalComment.toString())
        }
    }
}

@Preview
@Composable
private fun ForumPostCardPrev() {
    Surface {
        val data = Forum(
            profileUrl = "",
            name = "Sendiko",
            timeStamp = "1 jam yang lalu",
            content = "Lorem ipsum dolor sit amet suknedy ndsourtun gnasdjsfy sdjrosufdn.",
            totalComment = 69
        )
        ForumPostCard(forum = data)
    }
}