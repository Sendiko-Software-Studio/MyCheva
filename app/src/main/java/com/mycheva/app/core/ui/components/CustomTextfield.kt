package com.mycheva.app.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.theme.Neutral300
import com.mycheva.app.core.ui.theme.Neutral700
import com.mycheva.app.core.ui.theme.Neutral800
import com.mycheva.app.core.ui.theme.Neutral900
import com.mycheva.app.core.ui.theme.Primary300
import com.mycheva.app.core.ui.theme.Primary700
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    onClearClick: () -> Unit,
    leadingIcon: @Composable () -> Unit = {},
    keyboardOption: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    supportingText: String = "",
    type: TextFieldType = TextFieldType.Primary,
    singleLine: Boolean = true,
) {
    val containerColor = if (type == TextFieldType.Primary) Primary300 else Neutral300
    val contentColor = if (type == TextFieldType.Primary) Primary700 else Neutral700
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        isError = isError,
        keyboardOptions = keyboardOption,
        singleLine = singleLine,
        textStyle = TextStyle(fontFamily = poppinsFamily),
        shape = RoundedCornerShape(8.dp),
        supportingText = {
            Text(text = supportingText, fontFamily = poppinsFamily)
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
        placeholder = {
            Text(text = label, fontFamily = poppinsFamily)
        },
        trailingIcon = {
            AnimatedVisibility(visible = value.isNotBlank()) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear",
                        tint = contentColor
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun CustomTextFieldPrev() {
    Surface {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = "",
            onValueChange = {},
            label = "Username",
            onClearClick = { /*TODO*/ },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
        )
    }
}