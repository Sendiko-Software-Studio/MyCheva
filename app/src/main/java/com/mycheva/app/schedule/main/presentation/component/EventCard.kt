package com.mycheva.app.schedule.main.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.Neutral200
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.schedule.core.EventsItem

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    eventsItem: EventsItem
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Primary500,
            contentColor = Neutral200
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = eventsItem.name,
                fontSize = 16.sp,
                fontFamily = poppinsFamily
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.AccessTime,
                    contentDescription = "Jam"
                )
                Text(
                    text = eventsItem.time,
                    fontSize = 16.sp,
                    fontFamily = poppinsFamily
                )
            }
        }
    }
}