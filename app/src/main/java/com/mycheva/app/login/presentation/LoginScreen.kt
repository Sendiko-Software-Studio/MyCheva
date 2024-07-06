package com.mycheva.app.login.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import com.mycheva.app.core.ui.components.ContentBoxWithNotification
import com.mycheva.app.core.ui.theme.Primary500
import androidx.compose.ui.res.painterResource as painterResource1

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    state: LoginScreenState,
    onEvent: (LoginScreenEvent) -> Unit
) {
    ContentBoxWithNotification(message = "") {
        Scaffold {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource1(id = R.drawable.chevalier_logo),
                    contentDescription = "logo cheva",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .size(256.dp)
                )
                Text(
                    text = "Login",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
                Text(
                    text = "Username",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.usernameText,
                    onValueChange = { onEvent(LoginScreenEvent.OnUsernameChanged(it)) },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Image(
                            modifier = Modifier
                                .size(32.dp)
                                .padding(vertical = 0.dp, horizontal = 4.dp)
                                .padding(bottom = 8.dp),
                            painter = painterResource1(id = R.drawable.username_logo),
                            contentDescription = "logo username"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Password",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.passwordText,
                    onValueChange = { onEvent(LoginScreenEvent.OnPasswordChanged(it)) },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Image(
                            modifier = Modifier
                                .size(30.dp)
                                .padding(horizontal = 4.dp)
                                .padding(bottom = 8.dp),
                            painter = painterResource1(id = R.drawable.password_logo),
                            contentDescription = "logo password"
                        )
                    },
                    trailingIcon = {
                        val icon = if (state.isPasswordVisible) Icons.Rounded.Visibility
                        else Icons.Rounded.VisibilityOff
                        IconButton(
                            onClick = { onEvent(LoginScreenEvent.OnPasswordVisibilityToggle(!state.isPasswordVisible)) },
                            content = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "password"
                                )
                            }
                        )
                    }
                )
                Text(
                    text = "Lupa Password?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary500
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    onClick = { },
                    content = {
                        Text(text = "Login")
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen(
        state = LoginScreenState(),
        onEvent = { }
    )
}