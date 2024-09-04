package com.mycheva.app.roadmap.domain

import com.mycheva.app.roadmap.data.GetRoadMapResponse
import kotlinx.coroutines.flow.Flow

interface RoadMapRepository {

    fun getToken(): Flow<String>

    suspend fun getRoadMaps(token: String): Result<GetRoadMapResponse>

}