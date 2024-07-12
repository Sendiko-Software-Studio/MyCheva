package com.mycheva.app.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R

@Composable
fun UbahUsernameScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            color = Color(0xFFFFA500),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 24.dp, top = 50.dp, bottom = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "back",
                    modifier = Modifier

                        .size(24.dp)
                        .clickable { }
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Ubah Username",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.icon_ceklis),
                    contentDescription = "ceklis",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column (
            Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Username",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                //textAlign = TextAlign.Start
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "Kapal Laut",
                onValueChange = {  },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                ),
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.username_logo),
                        contentDescription = "logo username"
                    )
                }
            )
        }
        }

}