package com.mycheva.app.roadmap.presentation

import com.mycheva.app.roadmap.data.RoadmapsItem

data class RoadMapState(
    val roadMaps: List<RoadmapsItem> = emptyList(),
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: String = "",
    val token: String = "",
    val divisionId: String = "",
)
