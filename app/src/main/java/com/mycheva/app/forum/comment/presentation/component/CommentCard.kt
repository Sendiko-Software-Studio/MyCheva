package com.mycheva.app.forum.comment.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.mycheva.app.core.ui.components.timeAgo
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.forum.core.data.RepliesItem
import defaultProfile

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentCard(
    modifier: Modifier = Modifier,
    comment: RepliesItem,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
                    model = comment.user.profileUrl.ifBlank { defaultProfile },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = comment.user.name,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFamily,
                    fontSize = 16.sp
                )
            }
            Text(
                text = timeAgo(comment.createdAt),
                color = Color.Gray,
                fontFamily = poppinsFamily,
                fontSize = 12.sp
            )
        }
        Text(text = comment.content, fontFamily = poppinsFamily)
    }
}