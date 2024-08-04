package com.mycheva.app.profile.main.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mycheva.app.core.ui.theme.Primary500

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    onEditImage: () -> Unit
) {
    Box(
        modifier = modifier.size(128.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            model = imageUrl,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop
        )
        IconButton(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.BottomEnd)
                .background(color = Primary500, shape = CircleShape),
            onClick = onEditImage
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit",
                tint = Color.White
            )
        }
    }
}