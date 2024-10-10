package com.mycheva.app.roadmap.presentation

sealed class RoadMapEvent {
    data object OnClearState: RoadMapEvent()
    data class OnLoadData(val token: String, val divisionId: String): RoadMapEvent()
}
