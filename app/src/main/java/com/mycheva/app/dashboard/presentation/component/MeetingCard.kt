package com.mycheva.app.dashboard.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.components.formatDateString
import com.mycheva.app.core.ui.theme.Info
import com.mycheva.app.core.ui.theme.Neutral50
import com.mycheva.app.core.ui.theme.Neutral900
import com.mycheva.app.core.ui.theme.Primary300
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.schedule.core.EventsItem

@Composable
fun MeetingCard(
    modifier: Modifier = Modifier,
    eventsItem: EventsItem,
    onClick: (eventId: String) -> Unit,
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Primary300,
            contentColor = Neutral900,
        ),
        onClick = { onClick(eventsItem.id.toString()) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = eventsItem.name,
                    fontFamily = poppinsFamily,
                    fontSize = 18.sp,
//                    modifier = Modifier.fillMaxWidth()
                )
                Surface(
                    color = Info,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = eventsItem.type.uppercase(),
                        fontFamily = poppinsFamily,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Neutral50
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.CalendarToday,
                    contentDescription = "event"
                )
                Text(
                    text = "${formatDateString(eventsItem.date.substring(0, 10))}, ${eventsItem.time.substring(0, 5)}",
                    fontFamily = poppinsFamily
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.LocationOn,
                    contentDescription = "event"
                )
                Text(
                    text = eventsItem.details,
                    fontFamily = poppinsFamily
                )
            }
        }
    }
}

@Preview
@Composable
private fun MeetingCardPrev() {
    val data = EventsItem(
        date = "2005-07-26",
        createdAt = "",
        name = "Arrival of Destiny Takers",
        details = "Indonesia",
        id = 0,
        time = "00.00",
        divisionId = 0,
        type = "ONSITE",
        desc = "The day where gifted son born, destinied to take eveything",
        updatedAt = ""
    )

    MeetingCard(eventsItem = data, onClick = {})
}