package com.mycheva.app.core.network

import com.mycheva.app.announcement.data.dto.AnnouncementResponse
import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.data.AttendanceResponse
import com.mycheva.app.core.data.GetEventsResponse
import com.mycheva.app.core.data.GetUserResponse
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.forum.add.data.ForumPostRequest
import com.mycheva.app.forum.add.data.ForumPostResponse
import com.mycheva.app.forum.comment.data.GetForumResponse
import com.mycheva.app.forum.comment.data.PostReplyRequest
import com.mycheva.app.forum.comment.data.PostReplyResponse
import com.mycheva.app.forum.main.data.GetForumsResponse
import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse
import com.mycheva.app.meeting.detail.data.GetMeetingResponse
import com.mycheva.app.profile.main.data.ChangePasswordRequest
import com.mycheva.app.profile.main.data.ChangePasswordResponse
import com.mycheva.app.profile.main.data.ChangeUsernameRequest
import com.mycheva.app.profile.main.data.ChangeUsernameResponse
import com.mycheva.app.profile.main.data.LogoutResponse
import com.mycheva.app.reset_password.data.ResetPasswordRequest
import com.mycheva.app.reset_password.data.ResetPasswordResponse
import com.mycheva.app.roadmap.data.GetRoadMapResponse

interface ApiServices {

    suspend fun login(
        loginRequest: LoginRequest
    ): Result<LoginResponse, DataError.Remote>

    suspend fun getUser(
        token: String,
        id: String
    ): Result<GetUserResponse, DataError.Remote>

    suspend fun getEvents(
        token: String,
    ): Result<GetEventsResponse, DataError.Remote>

    suspend fun getEvent(
        token: String,
        eventId: String
    ): Result<GetMeetingResponse, DataError.Remote>

    suspend fun postAttendance(
        token: String,
        request: AttendanceRequest
    ): Result<AttendanceResponse, DataError.Remote>

    suspend fun resetPassword(
        token: String,
        request: ResetPasswordRequest
    ): Result<ResetPasswordResponse, DataError.Remote>

    suspend fun changePassword(
        token: String,
        userId: String,
        request: ChangePasswordRequest
    ): Result<ChangePasswordResponse, DataError.Remote>

    suspend fun changeUsername(
        token: String,
        userId: String,
        request: ChangeUsernameRequest
    ): Result<ChangeUsernameResponse, DataError.Remote>

    suspend fun getAnnouncements(
        token: String,
    ): Result<AnnouncementResponse, DataError.Remote>

    suspend fun getForums(
        token: String,
    ): Result<GetForumsResponse, DataError.Remote>

    suspend fun postForum(
        token: String,
        request: ForumPostRequest
    ): Result<ForumPostResponse, DataError.Remote>

    suspend fun getForum(
        token: String,
        forumId: String
    ): Result<GetForumResponse, DataError.Remote>

    suspend fun postReply(
        token: String,
        request: PostReplyRequest
    ): Result<PostReplyResponse, DataError.Remote>

    suspend fun getRoadMap(
        token: String,
    ): Result<GetRoadMapResponse, DataError.Remote>

    suspend fun logout(
        token: String
    ): Result<LogoutResponse, DataError.Remote>

}