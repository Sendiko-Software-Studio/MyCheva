package com.mycheva.app.schedule.main.data

import com.mycheva.app.core.data.EventsItem
import java.time.LocalDate

data class EventByDate(
    val date: LocalDate,
    val events: List<EventsItem>
)
