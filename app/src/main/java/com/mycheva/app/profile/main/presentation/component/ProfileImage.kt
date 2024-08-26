package com.mycheva.app.profile.main.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    onEditImage: () -> Unit
) {
    Box(
        modifier = modifier.size(76.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            model = imageUrl.ifBlank { "https://static.vecteezy.com/system/resources/previews/007/409/979/original/people-icon-design-avatar-icon-person-icons-people-icons-are-set-in-trendy-flat-style-user-icon-set-vector.jpg" },
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
        )
    }
}