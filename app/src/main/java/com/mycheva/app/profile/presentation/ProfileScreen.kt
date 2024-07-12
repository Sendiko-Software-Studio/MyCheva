package com.mycheva.app.profile.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.profile.presentation.component.ProfileButtons
import com.mycheva.app.profile.presentation.component.ProfileDetails
import com.mycheva.app.profile.presentation.component.ProfileImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
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
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.size(24.dp))
                    Text(
                        text = "Profile",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
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
            ) {
                ProfileImage(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = (-75).dp)
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
}

@Preview
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen()
}
