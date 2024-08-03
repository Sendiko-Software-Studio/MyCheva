package com.mycheva.app.reset_password

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.theme.Primary500
import androidx.compose.ui.res.painterResource as painterResource1


@Composable
fun ResetPasswordScreen() {
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
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Lupa password anda?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(64.dp))
        CustomTextField(
            label = "Masukkan email anda",
            value = "",
            onValueChange = { },
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
            onClick = {},
            content = {
                Text(text = "Reset Password")
            },
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Kembali ke halaman login",
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun ResetPasswordScreenPrev() {
    ResetPasswordScreen()
}