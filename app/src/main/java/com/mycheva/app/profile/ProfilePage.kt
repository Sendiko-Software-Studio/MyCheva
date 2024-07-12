package com.mycheva.app.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFA000))
                .height(200.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 150.dp, end = 24.dp, top = 50.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Profile",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Logout",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            ProfileImage(
               modifier = Modifier.align(Alignment.TopCenter)
                   .offset(y = -75.dp)
            )
        }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                ProfileDetails()
                ProfileButtons()
            }
    }
}

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

@Composable
fun ProfileDetails() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
    ) {
        ProfileDetailRow(label = "Username", value = "Kapal Laut")
        ProfileDetailRow(label = "Nama", value = "Budiono Siregar")
        ProfileDetailRow(label = "NIM", value = "1234567890")
        ProfileDetailRow(label = "Fakultas", value = "Kedokteran")
        ProfileDetailRow(label = "Jurusan", value = "Tata boga")
        ProfileDetailRow(label = "Divisi", value = "UI/UX")
    }
}

@Composable
fun ProfileDetailRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Black
        )
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ProfileButtons() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
        ) {
            Text(text = "Ubah username", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
        ) {
            Text(text = "Ubah password", color = Color.White)
        }
    }
}
