package com.mycheva.app.attendance.domain

import com.mycheva.app.attendance.data.dto.AttendanceRequest
import com.mycheva.app.attendance.data.dto.AttendanceResponse
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun postAttendance(token: String, eventId: String, userId: String): Result<AttendanceResponse, DataError.Remote>

}