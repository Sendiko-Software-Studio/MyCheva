package com.mycheva.app.core.network

import com.mycheva.app.announcement.data.AnnouncementResponse
import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.data.AttendanceResponse
import com.mycheva.app.dashboard.data.GetEventsResponse
import com.mycheva.app.forum.add.data.ForumPostRequest
import com.mycheva.app.forum.add.data.ForumPostResponse
import com.mycheva.app.forum.comment.data.GetForumResponse
import com.mycheva.app.forum.comment.data.PostReplyRequest
import com.mycheva.app.forum.comment.data.PostReplyResponse
import com.mycheva.app.forum.main.data.GetForumsResponse
import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse
import com.mycheva.app.profile.edit_pass.data.ChangePasswordRequest
import com.mycheva.app.profile.edit_pass.data.ChangePasswordResponse
import com.mycheva.app.profile.edit_username.data.ChangeUsernameRequest
import com.mycheva.app.profile.edit_username.data.ChangeUsernameResponse
import com.mycheva.app.profile.main.data.GetUserResponse
import com.mycheva.app.profile.main.data.LogoutResponse
import com.mycheva.app.reset_password.data.ResetPasswordRequest
import com.mycheva.app.reset_password.data.ResetPasswordResponse
import com.mycheva.app.roadmap.data.GetRoadMapResponse
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

    @POST("change_username/{id}")
    fun changeUsername(
        @Header("Authorization") token: String,
        @Path("id") userId: String,
        @Body request: ChangeUsernameRequest
    ): Call<ChangeUsernameResponse>

    @GET("announcement")
    fun getAnnouncements(
        @Header("Authorization") token: String,
    ): Call<AnnouncementResponse>

    @GET("forum")
    fun getForums(
        @Header("Authorization") token: String,
    ): Call<GetForumsResponse>

    @POST("forum")
    fun postForum(
        @Header("Authorization") token: String,
        @Body request: ForumPostRequest
    ): Call<ForumPostResponse>

    @GET("forum/{id}")
    fun getForum(
        @Header("Authorization") token: String,
        @Path("id") forumId: String
    ): Call<GetForumResponse>

    @POST("replies")
    fun postReply(
        @Header("Authorization") token: String,
        @Body request: PostReplyRequest
    ): Call<PostReplyResponse>

    @GET("roadmap")
    fun getRoadMap(
        @Header("Authorization") token: String,
    ): Call<GetRoadMapResponse>

    @GET("logout")
    fun logout(
        @Header("Authorization") token: String
    ): Call<LogoutResponse>

}