package com.mycheva.app.login.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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

@OptIn(ExperimentalSharedTransitionApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onNavigate: (destination: Any) -> Unit,
) {

    LaunchedEffect(key1 = state.isSignInFailed) {
        if (state.isSignInFailed) {
            delay(1000)
            onEvent(LoginEvent.OnClearState)
        }
    }

    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful){
            delay(1000)
            onNavigate(MainGraph)
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
                    Image(
                        modifier = Modifier
                            .size(256.dp),
                        painter = painterResource1(id = R.drawable.chv),
                        contentDescription = "logo cheva"
                    )
                    Column(
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 24.sp,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = poppinsFamily,
                            fontWeight = Bold
                        )
                        Text(
                            text = "Silahkan login dengan username dan password",
                            fontSize = 16.sp,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = poppinsFamily,
                        )
                    }
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.usernameText,
                        label = "Username",
                        isError = state.usernameTextState.isError,
                        supportingText = state.usernameTextState.errorMessage,
                        onValueChange = {
                            onEvent(LoginEvent.OnUsernameChanged(it))
                        },
                        onClearClick = {
                            onEvent(LoginEvent.OnUsernameCleared)
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
                    CustomPasswordTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.passwordText,
                        label = "Password",
                        isVisible = state.isPasswordVisible,
                        isError = state.passwordTextState.isError,
                        supportingText = state.passwordTextState.errorMessage,
                        onValueChange = {
                            onEvent(LoginEvent.OnPasswordChanged(it))
                        },
                        onPasswordToggle = {
                            onEvent(LoginEvent.OnPasswordVisibilityToggle(it))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Lock,
                                contentDescription = "password"
                            )
                        },
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onNavigate(ResetPasswordScreen)
                            },
                        fontSize = 14.sp,
                        text = "Lupa Password?",
                        textAlign = TextAlign.End,
                        fontFamily = poppinsFamily,
                        textDecoration = TextDecoration.Underline,
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary500
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        onClick = { onEvent(LoginEvent.OnLogin) },
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
    LoginScreen(state = LoginState(), onNavigate = {}, onEvent = {})
}