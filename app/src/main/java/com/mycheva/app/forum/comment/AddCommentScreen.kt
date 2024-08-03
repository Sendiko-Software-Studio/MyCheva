package com.mycheva.app.announcement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R

@Composable
fun AddCommentScreen() {
    //HEADER
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFA500))
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                //BACK BUTTON
                Icon(
                    painter = painterResource(id = R.drawable.back_logo),
                    contentDescription = "Logo back",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )

                //FORUM TEXT
                Text(
                    text = "Forum",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 110.dp, vertical = 5.dp)
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

                    //POSTINGAN 1
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
                                .size(40.dp),
                        )

                        Column {
                            //NAMA
                            Text(
                                text = "Dias",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                            )

                            //JAM
                            Text(
                                text = "3 Jam Lalu",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        //DESKRIPSI
                        Text(
                            text = "Pemilihan metode yang cocok untuk user testing itu caranya bagaimana?",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .align(Alignment.Start)
                        ) {
                            //COMMENTAR LOGO
                            Text(
                                text = "Komentar",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )

                            //BANYAK KOMENTAR
                            Text(
                                text = "(1)",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(15.dp))

                    //POSTINGAN 2
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .padding(top = 20.dp)
                    ) {
                        //PROFILE
                        Icon(
                            painter = painterResource(id = R.drawable.profile_logo),
                            contentDescription = "Logo profile",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(40.dp),
                        )

                        //NAMA
                        Text(
                            text = "Evan",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )

                        //JAM
                        Text(
                            text = "3 jam yang Lalu",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 5.dp)
                                .fillMaxWidth()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {

                        //DESKRIPSI
                        Text(
                            text = "pertama-tama kamu harus tahu apa tujuan kamu—misalnya, apakah kamu ingin menguji kemudahan penggunaan atau hanya mendapatkan feedback awal. Selain itu, sesuaikan dengan tahap proyek kamu",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .align(Alignment.Start)
                        ) {
                            //KOMENTAR LOGO
                            Icon(
                                painter = painterResource(id = R.drawable.comment_logo),
                                contentDescription = "Logo Komentar",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(25.dp)
                            )

                            //BANYAK KOMENTAR
                            Text(
                                text = "2",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )
                        }
                    }
                    //ADD KOMENTAR
                        //PROFILE
                        Spacer(modifier = Modifier.size(50.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            //PROFILE
                            Icon(
                                painter = painterResource(id = R.drawable.profile_logo),
                                contentDescription = "Logo profile",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(55.dp)
                                    .padding(top = 12.dp)
                            )

                            //TEXTFIELD
                            TextField(
                                modifier = Modifier
                                    .size(270.dp),
                                value = "",
                                onValueChange = {},
                                placeholder = {Text(text = "Berikan komentar anda")},
                                colors = TextFieldDefaults.colors(
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent
                                )
                            )

                            //LOGO KIRIM KOMEN
                            Icon(
                                painter = painterResource(id = R.drawable.send_logo),
                                contentDescription = "Logo Kirim",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(38.dp)
                                    .padding(top = 18.dp)
                            )
                        }
                    }
                }
            }
    )
}


@Preview
@Composable
private fun AddCommmentScreenPrev() {
    AddCommentScreen()
}