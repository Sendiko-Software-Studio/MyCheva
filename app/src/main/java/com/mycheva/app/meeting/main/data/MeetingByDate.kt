package com.mycheva.app.meeting.main.data

import com.mycheva.app.meeting.core.data.MeetingsItem
import java.time.LocalDate

data class MeetingByDate(
    val date: LocalDate,
    val events: List<MeetingsItem>
)
