package com.mycheva.app.schedule.main.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.poppinsFamily
import java.util.Calendar
import java.util.Locale

@Composable
fun Calendar(paddingValues: PaddingValues) {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)


    val dates = mutableListOf<Calendar>()
    repeat(7) {
        dates.add(Calendar.getInstance().apply { timeInMillis = calendar.timeInMillis })
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Juli",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dates.forEach { date ->
                date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())?.let {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontFamily = poppinsFamily
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dates.forEachIndexed { index, date ->
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${date.get(Calendar.DAY_OF_MONTH)}",
                        fontSize = 16.sp,
                        fontFamily = poppinsFamily
                    )
                    Spacer(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .background(if (index == 2) Color.Blue else Color.Transparent)
                    )
                }
            }
        }
    }
}