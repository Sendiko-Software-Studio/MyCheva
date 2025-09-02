package com.mycheva.app.roadmap.data.dto

import com.google.gson.annotations.SerializedName

data class GetRoadMapResponse(

	@field:SerializedName("roadmaps")
	val roadmaps: List<RoadmapsItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)