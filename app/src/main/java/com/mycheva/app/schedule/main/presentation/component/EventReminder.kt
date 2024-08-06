package com.mycheva.app.schedule.main.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R

@Composable
fun EventReminder(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Text(
            text = "Jul 3",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier
            .height(8.dp)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFE0F7FA),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Study group ke-3",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier
                .height(4.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.jam_icon),
                    contentDescription = "Logo back",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "18.30 - 19.30",
                    fontSize = 16.sp
                )
            }
        }
    }
}