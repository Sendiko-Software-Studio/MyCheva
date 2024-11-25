package com.mycheva.app.meeting.main.data

import com.mycheva.app.core.data.EventsItem
import java.time.LocalDate

data class MeetingByDate(
    val date: LocalDate,
    val events: List<EventsItem>
)
