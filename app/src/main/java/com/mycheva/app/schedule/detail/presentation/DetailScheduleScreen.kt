package com.mycheva.app.schedule.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.ui.components.CenteredAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScheduleScreen() {
    Scaffold(
        topBar = {
            CenteredAppBar(
                title = "Detail Jadwal",
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigationAction = { }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                EventDetail(paddingValues)
                DescriptionSection(paddingValues)
                LocationSection(paddingValues)
            }
        }
    )
}

@Composable
fun EventDetail(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(text = "Study group ke-3", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Rabu 3 Juli 2024", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.jam_icon),
                contentDescription = "Logo back",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "18.30 - 19.30", fontSize = 16.sp)
        }
    }
}

@Composable
fun DescriptionSection(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Deskripsi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Pada pertemuan kali ini, kita akan membahas terkait dengan cara membuat user journey yang baik. Selain itu juga kita akan membahas apa itu use case dan user flow.",
            fontSize = 16.sp
        )
    }
}

@Composable
fun LocationSection(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Lokasi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Zoom meeting",
            fontSize = 16.sp
        )
        Text(
            text = "Link: www.zoom.us",
            fontSize = 16.sp
        )

    }
}