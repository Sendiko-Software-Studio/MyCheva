package com.mycheva.app.core.network

import com.mycheva.app.announcement.data.AnnouncementResponse
import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.data.AttendanceResponse
import com.mycheva.app.dashboard.data.GetEventsResponse
import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse
import com.mycheva.app.profile.edit_pass.data.ChangePasswordRequest
import com.mycheva.app.profile.edit_pass.data.ChangePasswordResponse
import com.mycheva.app.profile.main.data.GetUserResponse
import com.mycheva.app.profile.main.data.LogoutResponse
import com.mycheva.app.reset_password.data.ResetPasswordRequest
import com.mycheva.app.reset_password.data.ResetPasswordResponse
import com.mycheva.app.schedule.detail.data.GetEventResponse
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

    @GET("event/{id}")
    fun getEvent(
        @Header("Authorization") token: String,
        @Path("id") eventId: String
    ): Call<GetEventResponse>

    @POST("attendance")
    fun postAttendance(
        @Header("Authorization") token: String,
        @Body attendanceRequest: AttendanceRequest
    ): Call<AttendanceResponse>

    @POST("reset_password")
    fun resetPassword(
        @Header("Authorization") token: String,
        @Body request: ResetPasswordRequest
    ): Call<ResetPasswordResponse>

    @POST("change_password/{id}")
    fun changePassword(
        @Header("Authorization") token: String,
        @Path("id") userId: String,
        @Body request: ChangePasswordRequest
    ): Call<ChangePasswordResponse>

    @GET("announcement")
    fun getAnnouncements(
        @Header("Authorization") token: String,
    ): Call<AnnouncementResponse>

    @GET("logout")
    fun logout(
        @Header("Authorization") token: String
    ): Call<LogoutResponse>

}