package com.mycheva.app.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R

@Composable
fun DetailScheduleScreen() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFA500))
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_logo),
                    contentDescription = "Logo back",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )

                Text(
                    text = "Schedule",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 110.dp, vertical = 5.dp)
                )
            }
        },
        content = { paddingValues ->
            Column(modifier = Modifier
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
    Column(modifier = Modifier
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
    Column(modifier = Modifier
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