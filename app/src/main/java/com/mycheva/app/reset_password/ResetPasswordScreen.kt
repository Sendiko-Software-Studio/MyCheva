package com.mycheva.app.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.ui.theme.Primary500
import androidx.compose.ui.res.painterResource as painterResource1


@Composable
fun ResetPasswordScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .align(Alignment.TopCenter)
        ) {
            //LOGO CHEVA
            Image(
                painter = painterResource1(id = R.drawable.chevalier_logo),
                contentDescription = "logo cheva",
                modifier = Modifier
                    .size(130.dp)
            )

            //LOGIN TEXT
            Text(
                text = "Lupa password anda?",
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 75.dp, vertical = 40.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {

            Spacer(modifier = Modifier.height(280.dp))

            //USERNAME
            Text(
                text = "Masukkan email",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 20.dp)
            )

            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 0.dp, horizontal = 8.dp),
                colors = TextFieldDefaults
                    .colors(
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    ),

                prefix = {
                    Image(
                        painter = painterResource1(id = R.drawable.username_logo),
                        contentDescription = "logo username",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(vertical = 0.dp, horizontal = 5.dp)
                            .padding(bottom = 8.dp)
                    )
                }
            )

            //BUTTON
            Button(
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = Primary500
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 50.dp, horizontal = 10.dp),
                onClick = {},
                content = {
                    Text(text = "Reset Password")
                }
            )

            //BACK TO LOGIN
            Text(
                text = "Kembali ke halaman login",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun ResetPasswordScreenPrev() {
    ResetPasswordScreen()
}