package com.mycheva.app.forum.main.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ModeComment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mycheva.app.core.network.defaultProfile
import com.mycheva.app.core.ui.components.timeAgo
import com.mycheva.app.core.ui.theme.Neutral50
import com.mycheva.app.core.ui.theme.Neutral600
import com.mycheva.app.core.ui.theme.Neutral900
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.forum.core.data.ForumsItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForumPostCard(
    modifier: Modifier = Modifier,
    forum: ForumsItem?,
    onNavigate: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .shadow(elevation = 4.dp, shape = CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = Neutral50,
            contentColor = Neutral900
        ),
        onClick = { onNavigate(forum?.id.toString()) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    model = forum?.user?.profileUrl?.ifBlank { defaultProfile },
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                )
                Column {
                    Text(
                        text = forum?.user?.name ?: "",
                        fontFamily = poppinsFamily,
                        fontSize = 16.sp
                    )
                    Text(
                        text = timeAgo(forum?.updatedAt ?: ""),
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        color = Neutral600,
                        fontSize = 12.sp
                    )
                }
            }
            Text(
                text = forum?.content ?: "",
                fontFamily = poppinsFamily,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ModeComment,
                    contentDescription = "comment"
                )
                val isEmpty = forum?.replies?.isEmpty() ?: true
                val replies = if (isEmpty) "0" else forum!!.replies.size.toString()
                Text(text = replies, fontFamily = poppinsFamily)
            }
        }
    }
}