package com.mycheva.app.roadmap.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoadMapResponse(

	@SerialName("roadmaps")
	val roadmaps: List<RoadmapsItem>,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)