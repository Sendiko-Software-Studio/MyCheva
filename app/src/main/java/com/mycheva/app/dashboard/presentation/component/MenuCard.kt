package com.mycheva.app.dashboard.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun MenuCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
) {
    OutlinedCard(
        onClick = { /*TODO*/ },
        modifier = modifier
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = label,
                fontFamily = poppinsFamily,
                fontSize = 16.sp
            )
        }
    }
}