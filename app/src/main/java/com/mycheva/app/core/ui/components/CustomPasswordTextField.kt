package com.mycheva.app.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun CustomPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onPasswordToggle: (Boolean) -> Unit,
    label: String = "",
    isVisible: Boolean = false,
    leadingIcon: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontFamily = poppinsFamily
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = Color.Black,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedTrailingIconColor = Color.Black,
                focusedTrailingIconColor = Color.Black,
                unfocusedLeadingIconColor = Color.Black,
                focusedLeadingIconColor = Color.Black
            ),
            leadingIcon = leadingIcon,
            trailingIcon = {
                if (isVisible) {
                    IconButton(onClick = { onPasswordToggle(false) }) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "visible"
                        )
                    }
                } else {
                    IconButton(onClick = { onPasswordToggle(true) }) {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = "not visible"
                        )
                    }
                }
            },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            textStyle = TextStyle(fontFamily = poppinsFamily)
        )
    }
}