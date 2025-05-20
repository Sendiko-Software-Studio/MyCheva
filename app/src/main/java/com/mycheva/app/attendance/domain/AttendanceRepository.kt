package com.mycheva.app.attendance.domain

import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.data.AttendanceResponse
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {

    fun getToken(): Flow<String>

    fun getUserId(): Flow<String>

    suspend fun postAttendance(token: String, request: AttendanceRequest): Result<AttendanceResponse>

}