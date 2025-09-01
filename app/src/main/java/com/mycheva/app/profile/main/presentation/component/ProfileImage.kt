package com.mycheva.app.profile.main.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mycheva.app.R

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    onEditImage: () -> Unit
) {
    Log.i("PROFILE", "ProfileImage: $imageUrl")
    val url = imageUrl.replace("http://", "https://")
    Box(
        modifier = modifier.size(76.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            model = url.ifBlank { "https://static.vecteezy.com/system/resources/previews/007/409/979/original/people-icon-design-avatar-icon-person-icons-people-icons-are-set-in-trendy-flat-style-user-icon-set-vector.jpg" },
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.image_error),
            onError = {
                Log.e("PROFILE", "ProfileImage: ${it.result.throwable.message}")
            }
        )
    }
}