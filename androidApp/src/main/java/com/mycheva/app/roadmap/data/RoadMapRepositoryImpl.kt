package com.mycheva.app.roadmap.data

import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.roadmap.data.dto.toDomain
import com.mycheva.app.roadmap.domain.RoadMap
import com.mycheva.app.roadmap.domain.RoadMapRepository
import kotlinx.coroutines.flow.Flow

class RoadMapRepositoryImpl(
    private val appPreferences: AppPreferences,
    private val client: KtorClient,
) : RoadMapRepository {

    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override suspend fun getRoadMaps(token: String): Result<List<RoadMap>, DataError.Remote> {
        val response = client.getRoadMap(token)

        return when (response) {
            is Result.Success -> {
                Result.Success(response.data.roadmaps.map { it.toDomain() })
            }

            is Result.Error -> {
                Result.Error(response.error)
            }

        }
    }

}