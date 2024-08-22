package com.mycheva.app.dashboard.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarViewDay
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.Neutral300
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun EmptyMeetingCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Neutral300
        )
    ) {
        Row(
            modifier = Modifier
                .height(132.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
           Icon(
               imageVector = Icons.Rounded.CalendarViewDay,
               contentDescription = "Calendar",
               modifier = Modifier.size(48.dp)
           )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Tidak ada pertemuan",
                fontSize = 16.sp,
                fontFamily = poppinsFamily
            )
        }
    }
}

@Preview
@Composable
private fun EmptyMeetingCardPrev() {
    EmptyMeetingCard(
        modifier = Modifier.fillMaxWidth()
    )
}