package com.mycheva.app.roadmap.presentation

import com.mycheva.app.core.ui.utils.UiText

data class RoadMapState(
    val roadMaps: List<RoadMapUi> = emptyList(),
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val token: String = "",
    val divisionId: String = "",
)
