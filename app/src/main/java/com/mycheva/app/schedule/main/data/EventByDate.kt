package com.mycheva.app.schedule.main.data

import com.mycheva.app.dashboard.data.EventsItem
import java.time.LocalDate

data class EventByDate(
    val date: LocalDate,
    val events: List<EventsItem>
)
