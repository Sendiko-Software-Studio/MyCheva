package com.mycheva.app.forum.comment.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
import com.mycheva.app.forum.comment.data.Comment

@Composable
fun CommentCard(
    modifier: Modifier = Modifier,
    comment: Comment,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = comment.profileUrl,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = comment.name,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = comment.timeStamp,
                color = Color.Gray
            )
        }
        Text(text = comment.content)
    }
}

@Preview
@Composable
private fun CommentCardPrev() {
    Surface {
        val data = Comment(
            profileUrl = "",
            timeStamp = "1 jam yang lalu",
            name = "Sendiko",
            content = "Lorem ipsum dolor sit amet suknedy ndsourtun gnasdjsfy sdjrosufdn."
        )
        CommentCard(
            comment = data,
            modifier = Modifier.fillMaxWidth()
        )
    }
}