package com.mycheva.app.roadmap.domain

import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.roadmap.data.dto.GetRoadMapResponse
import kotlinx.coroutines.flow.Flow

interface RoadMapRepository {

    fun getToken(): Flow<String>

    suspend fun getRoadMaps(token: String): Result<List<RoadMap>, DataError.Remote>

}