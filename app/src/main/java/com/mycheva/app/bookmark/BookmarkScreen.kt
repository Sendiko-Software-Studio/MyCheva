package com.mycheva.app.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

@Composable
fun BookmarkScreen() {
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
                            .padding(vertical = 8.dp)
                    )

                    //BOOKMARK TEXT
                    Text(
                        text = "Bookmark",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 80.dp, vertical = 8.dp)
                    )

                }
            },

            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Spacer(modifier = Modifier.size(15.dp))

                    //POSTINGAN 1
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
                                .size(40.dp),
                        )

                        Column {
                            //NAMA
                            Text(
                                text = "Daffa Fadilah",
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
                    Image(
                        painter = painterResource(id = R.drawable.postingan),
                        contentDescription = "Ilustrasi postingan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(200.dp)
                            .padding(vertical = 8.dp)
                            .align(Alignment.CenterHorizontally)


                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        //DESKRIPSI
                        Text(
                            text = "Jangan lupa pengerjaannya sesuai sama yang dibilang kordas kemarin, minggu depan kita ada presentasi progres tubes pertama. Semangat guyss",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                        )

                        //BOOKMARK LOGO
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark_black_logo),
                            contentDescription = "Logo Non Bookmark",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            }
        )
    }

@Preview
@Composable
private fun BookmarkScreenPrev() {
    BookmarkScreen()
}