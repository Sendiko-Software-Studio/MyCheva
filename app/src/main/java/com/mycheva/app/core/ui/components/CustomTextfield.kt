package com.mycheva.app.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    onClearClick: () -> Unit,
    leadingIcon: @Composable () -> Unit = {},
    keyboardOption: KeyboardOptions = KeyboardOptions()
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
                AnimatedVisibility(visible = value.isNotBlank()) {
                    IconButton(onClick = onClearClick) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "clear"
                        )
                    }
                }
            },
            keyboardOptions = keyboardOption,
            textStyle = TextStyle(fontFamily = poppinsFamily)
        )
    }
}

@Preview
@Composable
private fun CustomTextFieldPrev() {
    Surface {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = "Sendiko",
            onValueChange = {},
            label = "Username",
            onClearClick = { /*TODO*/ },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            }
        )
    }
}