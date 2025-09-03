package com.mycheva.app.profile.main.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.theme.Neutral50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onChange: (String) -> Unit,
    isLoading: Boolean = false,
    isSuccess: Boolean,
    isError: Boolean,
) {
    var username by remember {
        mutableStateOf("")
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Ubah username",
                fontFamily = poppinsFamily,
                fontSize = 18.sp
            )
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it },
                onClearClick = { username = "" },
                label = "Masukkan username baru",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "username"
                    )
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onChange(username) },
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
                        isSuccess -> "Username berhasil diubah."
                        else -> "Ubah"
                    },
                    fontFamily = poppinsFamily
                )
            }
        }
    }
}