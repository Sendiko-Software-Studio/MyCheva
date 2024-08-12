package com.mycheva.app.reset_password.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.Primary950
import com.mycheva.app.core.ui.theme.poppinsFamily
import kotlinx.coroutines.delay
import androidx.compose.ui.res.painterResource as painterResource1


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ResetPasswordScreen(
    state: ResetPasswordScreenState,
    onEvent: (ResetPasswordScreenEvent) -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
    onNavigateBack: () -> Unit,
) {

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotEmpty()) {
            delay(2000)
            onEvent(ResetPasswordScreenEvent.ClearState)
        }
    }

    LaunchedEffect(key1 = state.isRequestSuccess) {
        if (state.isRequestSuccess){
            delay(2000)
            onNavigateBack()
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isRequestFailed
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
        ) {
            Image(
                painter = painterResource1(id = R.drawable.chevalier_logo),
                contentDescription = "logo cheva",
                modifier = Modifier
                    .size(128.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "chevalier_logo"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Lupa password anda?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = poppinsFamily
            )
            Spacer(modifier = Modifier.height(64.dp))
            CustomTextField(
                label = "Masukkan email anda",
                value = state.emailText,
                onValueChange = { onEvent(ResetPasswordScreenEvent.OnEmailTextChange(it)) },
                onClearClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "email")
                }
            )
            Button(
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = Primary500
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    onEvent(ResetPasswordScreenEvent.OnResetPassword(state.token, state.emailText))
                },
                content = {
                    Text(text = "Reset Password")
                },
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = onNavigateBack,
                content = {
                    Text(
                        text = "Kembali ke halaman login",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontFamily = poppinsFamily,
                        color = Primary950
                    )
                },
            )
        }
    }
}