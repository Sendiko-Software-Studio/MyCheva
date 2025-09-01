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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mycheva.app.core.network.defaultProfile
import com.mycheva.app.core.ui.components.timeAgo
import com.mycheva.app.core.ui.theme.Neutral600
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.forum.main.presentation.ForumUi
import com.mycheva.app.forum.main.presentation.component.RoleChip

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    forum: ForumUi
) {
    val url = forum.profileUrl.replace("http://", "https://")
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                model = url.ifBlank { defaultProfile },
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = forum.username,
                        fontFamily = poppinsFamily,
                        fontSize = 18.sp
                    )
                    RoleChip(role = forum.role)
                }
                Text(
                    text = timeAgo(forum.time),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    color = Neutral600,
                    fontSize = 14.sp
                )
            }
        }
        Text(
            text = forum.content,
            fontFamily = poppinsFamily,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp),
            fontSize = 16.sp
        )
        HorizontalDivider(color = Primary500, thickness = 2.dp)
    }
}