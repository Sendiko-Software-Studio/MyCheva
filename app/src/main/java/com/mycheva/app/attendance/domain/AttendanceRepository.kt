package com.mycheva.app.attendance.domain

import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AttendanceRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val appPreferences: AppPreferences
) {

    fun getToken(): Flow<String> {
        return appPreferences.getToken()
    }

    fun getUserId(): Flow<String> {
        return appPreferences.getUserId()
    }

    fun postAttendance(token: String, request: AttendanceRequest) = apiServices.postAttendance(token, request)

}