package com.mycheva.app.forum.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.ui.theme.Primary500

@Composable
fun AddPostinganScreen() {
    //HEADER
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFA500))
                    .padding(horizontal = 10.dp, vertical = 12.dp)
            ) {
                //BACK BUTTON
                Icon(
                    painter = painterResource(id = R.drawable.back_logo),
                    contentDescription = "Logo back",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(30.dp)
                )

                //JUDUL
                Text(
                    text = "Buat Postingan",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 86.dp, vertical = 5.dp)
                )
            }
        },

        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                    //.padding(bottom = 60.dp)
                ) {
                    Spacer(modifier = Modifier.size(15.dp))

                    //TAMBAH POSTINGAN
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(8.dp)
                    ) {
                        //PROFILE
                        Icon(
                            painter = painterResource(id = R.drawable.profile_logo),
                            contentDescription = "Logo profile",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(top = 12.dp)
                        )

                        //TEXTFIELD
                        TextField(
                            modifier = Modifier
                                .size(390.dp),
                            value = "",
                            onValueChange = {},
                            placeholder = {Text(text = "Apa yang ingin kita bahas?")},
                            colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(15.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        //UNGGAH GAMBAR
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary500
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            content = {
                                Text(text = "Unggah gambar",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )

                            }
                        )

                        //KIRIM
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary500
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            content = {
                                Text(text = "Kirim",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                            }
                        )
                    }
                }
            }
        }
    )
}


@Preview
@Composable
private fun AddPostinganScreenPrev() {
    AddPostinganScreen()
}