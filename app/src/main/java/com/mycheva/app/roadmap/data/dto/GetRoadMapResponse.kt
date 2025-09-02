package com.mycheva.app.roadmap.data.dto

import kotlinx.serialization.SerialName

data class GetRoadMapResponse(

	@SerialName("roadmaps")
	val roadmaps: List<RoadmapsItem>,

	@SerialName("message")
	val message: String,

	@SerialName("status")
	val status: Int,
)