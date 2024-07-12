package com.mycheva.app.profile.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mycheva.app.R

@Composable
fun ProfileImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFA000))
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_cheva),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.icon_edit),
            contentDescription = "Edit",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.BottomEnd)
                .background(Color(0xFFFFA000), shape = CircleShape)
                .padding(8.dp)
                .clickable { }
        )
    }
}