package com.mycheva.app.roadmap.presentation

import com.mycheva.app.roadmap.domain.RoadMap

data class RoadMapUi(
    val divisionId: Int,
    val title: String,
    val desc: String,
) {
    companion object {
        fun fromRoadmap(roadMap: RoadMap): RoadMapUi {
            return RoadMapUi(
                divisionId = roadMap.divisionId,
                title = roadMap.title,
                desc = roadMap.desc
            )
        }
    }
}
