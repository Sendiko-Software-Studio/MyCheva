package com.mycheva.app.profile.main.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.Neutral600
import com.mycheva.app.core.ui.theme.Neutral900
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun ProfileDetailRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Neutral600,
            fontFamily = poppinsFamily
        )
        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Neutral900,
            fontFamily = poppinsFamily
        )
        HorizontalDivider(
            color = Neutral600,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}