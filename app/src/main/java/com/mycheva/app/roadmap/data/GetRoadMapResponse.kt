package com.mycheva.app.roadmap.data

import com.google.gson.annotations.SerializedName

data class GetRoadMapResponse(

	@field:SerializedName("roadmaps")
	val roadmaps: List<RoadmapsItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class RoadmapsItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("divisionId")
	val divisionId: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
