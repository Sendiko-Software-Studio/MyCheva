package com.mycheva.app.profile.main.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.components.CustomPasswordTextField
import com.mycheva.app.core.ui.theme.Error
import com.mycheva.app.core.ui.theme.Neutral50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordSheet(
    modifier: Modifier = Modifier,
    onChange: (pass: String, newPass: String) -> Unit,
    onDismiss: () -> Unit,
    isLoading: Boolean = false,
    isSuccess: Boolean,
    isError: Boolean,
) {
    var pass by remember {
        mutableStateOf("")
    }
    var newPass by remember {
        mutableStateOf("")
    }
    var isPassVisible by remember {
        mutableStateOf(false)
    }
    var isNewPassVisible by remember {
        mutableStateOf(false)
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Ubah password",
                fontFamily = poppinsFamily,
                fontSize = 18.sp
            )
            AnimatedVisibility(visible = isError) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Error, contentColor = Neutral50)
                ) {
                    Text(
                        text = "Password salah, mohon ulangi.",
                        fontFamily = poppinsFamily,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        fontSize = 12.sp
                    )
                }
            }
            CustomPasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = pass,
                onValueChange = { pass = it },
                onPasswordToggle = { isPassVisible = !isPassVisible },
                label = "Password Lama",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = "password"
                    )
                },
                isVisible = isPassVisible
            )
            CustomPasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = newPass,
                onValueChange = { newPass = it },
                onPasswordToggle = { isNewPassVisible = !isNewPassVisible },
                label = "Password baru",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = "password"
                    )
                },
                isVisible = isNewPassVisible
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onChange(pass, newPass) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary500,
                    contentColor = Neutral50
                ),
                shape = RoundedCornerShape(8.dp),
                enabled = !isLoading
            ) {
                Text(
                    text = when {
                        isLoading -> "Loading.."
                        isSuccess -> "Password berhasil diubah."
                        else -> "Ubah"
                    },
                    fontFamily = poppinsFamily
                )
            }
        }
    }
}