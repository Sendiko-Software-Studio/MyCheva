package com.mycheva.app.login.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.navigation.MainGraph
import com.mycheva.app.core.navigation.ResetPasswordScreen
import com.mycheva.app.core.ui.components.CustomPasswordTextField
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import kotlinx.coroutines.delay
import androidx.compose.ui.res.painterResource as painterResource1

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    state: LoginScreenState,
    onEvent: (LoginScreenEvent) -> Unit,
    onNavigate: (destination: Any) -> Unit
) {

    LaunchedEffect(key1 = state) {
        if (state.isSignInSuccessful)
            onNavigate(MainGraph)

        if (state.isSignInFailed) {
            delay(1000)
            onEvent(LoginScreenEvent.OnClearState)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isErrorNotification = state.isSignInFailed,
        isLoading = state.isLoading,
        content = {
            Scaffold {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(64.dp))
                    Image(
                        painter = painterResource1(id = R.drawable.chevalier_logo),
                        contentDescription = "logo cheva",
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .size(200.dp)
                    )
                    Text(
                        text = "Login",
                        fontSize = 32.sp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        fontFamily = poppinsFamily
                    )
                    CustomTextField(
                        value = state.usernameText,
                        label = "Username",
                        onValueChange = {
                            onEvent(LoginScreenEvent.OnUsernameChanged(it))
                        },
                        onClearClick = {
                            onEvent(LoginScreenEvent.OnUsernameCleared)
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )
                        },
                        keyboardOption = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomPasswordTextField(
                        value = state.passwordText,
                        label = "Password",
                        onValueChange = {
                            onEvent(LoginScreenEvent.OnPasswordChanged(it))
                        },
                        onPasswordToggle = {
                            onEvent(LoginScreenEvent.OnPasswordVisibilityToggle(it))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Lock,
                                contentDescription = "password"
                            )
                        },
                        isVisible = state.isPasswordVisible
                    )
                    Text(
                        text = "Lupa Password?",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .clickable {
                                onNavigate(ResetPasswordScreen)
                            },
                        fontFamily = poppinsFamily
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary500
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        onClick = { onEvent(LoginScreenEvent.OnLogin) },
                        content = {
                            Text(
                                text = "Login",
                                fontSize = 16.sp,
                                fontWeight = Bold,
                                fontFamily = poppinsFamily
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        enabled = !state.isLoading
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen(
        state = LoginScreenState(),
        onEvent = { },
        onNavigate = { }
    )
}