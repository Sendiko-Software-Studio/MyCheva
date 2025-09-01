package com.mycheva.app.attendance.data

import com.mycheva.app.attendance.data.dto.AttendanceRequest
import com.mycheva.app.attendance.data.dto.AttendanceResponse
import com.mycheva.app.attendance.domain.AttendanceRepository
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.network.utils.SERVER_ERROR
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AttendanceRepositoryImpl(
    private val client: KtorClient,
    private val appPreferences: AppPreferences
): AttendanceRepository {
    override fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    override fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    override suspend fun postAttendance(
        token: String,
        eventId: String,
        userId: String
    ): Result<AttendanceResponse, DataError.Remote> {
        val request = AttendanceRequest(
            eventId = eventId,
            userId = userId,
            status = "present"
        )
        return client.postAttendance(token, request)
    }
}