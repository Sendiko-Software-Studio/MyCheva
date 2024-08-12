package com.mycheva.app.core.network

import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.data.AttendanceResponse
import com.mycheva.app.dashboard.data.GetEventsResponse
import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse
import com.mycheva.app.profile.main.data.GetUserResponse
import com.mycheva.app.profile.main.data.LogoutResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {

    @POST("login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @GET("userdata/{id}")
    fun getUser(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<GetUserResponse>

    @GET("event")
    fun getEvents(
        @Header("Authorization") token: String,
    ): Call<GetEventsResponse>

    @POST("attendance")
    fun postAttendance(
        @Header("Authorization") token: String,
        @Body attendanceRequest: AttendanceRequest
    ): Call<AttendanceResponse>

    @GET("logout")
    fun logout(
        @Header("Authorization") token: String
    ): Call<LogoutResponse>

}