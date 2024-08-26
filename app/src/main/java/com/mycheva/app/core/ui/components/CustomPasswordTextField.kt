package com.mycheva.app.core.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.theme.Neutral300
import com.mycheva.app.core.ui.theme.Neutral700
import com.mycheva.app.core.ui.theme.Neutral800
import com.mycheva.app.core.ui.theme.Neutral900
import com.mycheva.app.core.ui.theme.Primary300
import com.mycheva.app.core.ui.theme.Primary700
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
    supportingText: String = "",
    type: TextFieldType = TextFieldType.Primary,
    singleLine: Boolean = true
) {
    val containerColor = if (type == TextFieldType.Primary) Primary300 else Neutral300
    val contentColor = if (type == TextFieldType.White) Primary700 else Neutral700
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        textStyle = TextStyle(fontFamily = poppinsFamily),
        shape = RoundedCornerShape(8.dp),
        singleLine = singleLine,
        supportingText = {
            Text(text = supportingText)
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = containerColor,
            focusedContainerColor = containerColor,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedLeadingIconColor = Neutral800,
            focusedLeadingIconColor = Neutral800,
            unfocusedTrailingIconColor = Neutral800,
            focusedTrailingIconColor = Neutral800,
            unfocusedPlaceholderColor = Neutral800,
            focusedPlaceholderColor = Neutral800,
            unfocusedTextColor = Neutral800,
            focusedTextColor = Neutral900,
            unfocusedSupportingTextColor = Neutral800,
            errorBorderColor = Color.Transparent,
            errorContainerColor = containerColor,
        ),
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (isVisible) {
                IconButton(onClick = { onPasswordToggle(false) }) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "visible",
                        tint = contentColor
                    )
                }
            } else {
                IconButton(onClick = { onPasswordToggle(true) }) {
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = "not visible",
                        tint = contentColor
                    )
                }
            }
        },
        placeholder = {
            Text(text = label, fontFamily = poppinsFamily)
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
    )
}