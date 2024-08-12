package com.mycheva.app.dashboard.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.dashboard.data.EventsItem

@Composable
fun MeetingCard(
    modifier: Modifier = Modifier,
    eventsItem: EventsItem
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Primary500,
            contentColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ElevatedAssistChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = eventsItem.type.uppercase(), fontSize = 12.sp) },
                )
                when (eventsItem.divisionId) {
                    1 -> StartUpAndCompeIcon()
                    2 -> GameDevIcon()
                    3 -> UiuxDevIcon()
                    4 -> WebDevIcon()
                    5 -> AndroDevIcon()
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = eventsItem.name,
                fontFamily = poppinsFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (eventsItem.type == "online") "Klik untuk Link" else eventsItem.details, fontFamily = poppinsFamily)
                Text(text = "${eventsItem.date.substring(0, 10)}, ${eventsItem.time.substring(0, 5)}", fontFamily = poppinsFamily)
            }
        }
    }
}