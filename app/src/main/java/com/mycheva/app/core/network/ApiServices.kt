package com.mycheva.app.core.network

import com.mycheva.app.announcement.data.dto.AnnouncementResponse
import com.mycheva.app.attendance.data.dto.AttendanceRequest
import com.mycheva.app.attendance.data.dto.AttendanceResponse
import com.mycheva.app.meeting.core.data.GetMeetingsResponse
import com.mycheva.app.profile.main.data.dto.getuser.GetUserResponse
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.forum.add.data.dto.ForumPostRequest
import com.mycheva.app.forum.add.data.dto.ForumPostResponse
import com.mycheva.app.forum.replies.data.dto.GetForumResponse
import com.mycheva.app.forum.replies.data.dto.PostReplyRequest
import com.mycheva.app.forum.replies.data.dto.PostReplyResponse
import com.mycheva.app.forum.main.data.dto.GetForumsResponse
import com.mycheva.app.login.data.dto.LoginRequest
import com.mycheva.app.login.data.dto.LoginResponse
import com.mycheva.app.meeting.core.data.GetMeetingResponse
import com.mycheva.app.profile.main.data.dto.changepassword.ChangePasswordRequest
import com.mycheva.app.profile.main.data.dto.changepassword.ChangePasswordResponse
import com.mycheva.app.profile.main.data.dto.changeusername.ChangeUsernameRequest
import com.mycheva.app.profile.main.data.dto.changeusername.ChangeUsernameResponse
import com.mycheva.app.profile.main.data.dto.LogoutResponse
import com.mycheva.app.reset_password.data.dto.ResetPasswordRequest
import com.mycheva.app.reset_password.data.dto.ResetPasswordResponse
import com.mycheva.app.roadmap.data.dto.GetRoadMapResponse

interface ApiServices {

    suspend fun login(
        loginRequest: LoginRequest
    ): Result<LoginResponse, DataError.Remote>

    suspend fun getUser(
        token: String,
        id: String
    ): Result<GetUserResponse, DataError.Remote>

    suspend fun getMeetings(
        token: String,
    ): Result<GetMeetingsResponse, DataError.Remote>

    suspend fun getMeeting(
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