package com.mycheva.app.forum.main.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.Neutral100
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun RoleChip(
    modifier: Modifier = Modifier,
    role: String
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = Primary500
    ) {
        Text(
            text = role,
            modifier = Modifier.padding(8.dp, 4.dp),
            fontFamily = poppinsFamily,
            fontSize = 14.sp,
            color = Neutral100
        )
    }
}