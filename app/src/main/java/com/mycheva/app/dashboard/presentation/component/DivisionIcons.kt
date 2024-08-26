package com.mycheva.app.dashboard.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Gamepad
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mycheva.app.R

@Composable
fun AndroDevIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF3DDC84)),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(id = R.drawable.ic_android_24dp),
            contentDescription = "Android",
            modifier = modifier.padding(4.dp)
        )
    }
}

@Composable
fun UiuxDevIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF2D2D35)),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(id = R.drawable.devicon_figma),
            contentDescription = "UI/UX",
            modifier = modifier.padding(4.dp)
        )
    }
}

@Composable
fun WebDevIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF16529)),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(id = R.drawable.mdi_web__1_),
            contentDescription = "Web",
            modifier = modifier.padding(4.dp)
        )
    }
}

@Composable
fun GameDevIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFCD2828)),
        contentAlignment = Alignment.Center
    ){
        Icon(
            imageVector = Icons.Rounded.Gamepad,
            contentDescription = "Web",
            modifier = modifier.padding(4.dp)
        )
    }
}

@Composable
fun StartUpAndCompeIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF147DD9)),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(id = R.drawable.streamline_startup_solid),
            contentDescription = "Web",
            modifier = modifier.padding(4.dp)
        )
    }
}