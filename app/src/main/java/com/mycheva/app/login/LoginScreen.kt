package com.mycheva.app.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun LoginScreen() {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {

            //LOGO CHEVA
            Image(
                painter = painterResource1(id = R.drawable.chevalier_logo),
                contentDescription = "logo cheva",
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 100.dp)
            )

            //LOGIN TEXT
            Text(
                text = "Login",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 140.dp)
                    .padding(bottom = 10.dp)
            )

            //USERNAME
            Text(
                text = "Username",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = 20.dp)
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

            //PASSWORD
            Text(
                text = "Password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = 20.dp)
                    .padding(top = 15.dp)
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
                        painter = painterResource1(id = R.drawable.password_logo),
                        contentDescription = "logo password",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(vertical = 0.dp, horizontal = 5.dp)
                            .padding(bottom = 8.dp)
                    )
                }
            )

            //FORGOT PASWORD
            Text(
                text = "Lupa Password?",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 20.dp)
            )

            //BUTTON
           Button(
               colors = ButtonDefaults
                   .buttonColors(
                    containerColor = Primary500
               ),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(vertical = 30.dp),
                onClick = {},
                content = {
                    Text(text = "Login")
                }
            )

        }
    }

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen()
}