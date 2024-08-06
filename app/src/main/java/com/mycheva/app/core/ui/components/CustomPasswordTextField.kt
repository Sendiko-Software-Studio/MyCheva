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
import com.mycheva.app.core.ui.theme.Error
import com.mycheva.app.core.ui.theme.Neutral900
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
    isError: Boolean = false,
    supportingText: String = ""
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
            textStyle = TextStyle(fontFamily = poppinsFamily),
            isError = isError,
            supportingText = {
                Text(text = supportingText,)
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Neutral900,
                focusedIndicatorColor = Neutral900,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedTrailingIconColor = Neutral900,
                focusedTrailingIconColor = Neutral900,
                unfocusedLeadingIconColor = Neutral900,
                focusedLeadingIconColor = Neutral900,
                errorContainerColor = Color.Transparent,
                errorIndicatorColor = Error,
                errorLeadingIconColor = Error,
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
            )
        )
    }
}