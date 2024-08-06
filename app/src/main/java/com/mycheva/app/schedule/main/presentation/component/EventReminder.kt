package com.mycheva.app.schedule.main.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.poppinsFamily
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun EventReminder(paddingValues: PaddingValues) {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
    val formattedDate = dateFormat.format(calendar.time)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = formattedDate,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFamily,
        )
        EventCard()
    }
}