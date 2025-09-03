package com.mycheva.app.meeting.main.presentation

import com.mycheva.app.meeting.core.domain.Meeting

data class MeetingUi(
    val id: String,
    val name: String,
    val date: String,
    val time: String,
    val type: String,
    val place: String,
) {
    fun toDomain(): Meeting {
        return Meeting(
            name = name,
            date = date,
            time = time,
            type = type,
            place = place,
            id = id,
            divisionId = 0
        )
    }

    companion object {
        fun fromDomain(meeting: Meeting): MeetingUi {
            return MeetingUi(
                name = meeting.name,
                date = meeting.date,
                time = meeting.time,
                type = meeting.type,
                place = meeting.place,
                id = meeting.id
            )
        }
    }
}