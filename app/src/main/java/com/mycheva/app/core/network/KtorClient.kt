package com.mycheva.app.core.network

import com.mycheva.app.announcement.data.dto.AnnouncementResponse
import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.data.AttendanceResponse
import com.mycheva.app.core.data.GetEventsResponse
import com.mycheva.app.core.data.GetUserResponse
import com.mycheva.app.core.network.utils.DataError
import com.mycheva.app.core.network.utils.LIVE_SERVER
import com.mycheva.app.core.network.utils.Result
import com.mycheva.app.core.network.utils.safeCall
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
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class KtorClient(
    private val client: HttpClient
) : ApiServices {

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, DataError.Remote> {
        return safeCall<LoginResponse> {
            client.post("$LIVE_SERVER/login") {
                setBody(loginRequest)
            }
        }
    }

    override suspend fun getUser(
        token: String,
        id: String
    ): Result<GetUserResponse, DataError.Remote> {
        return safeCall<GetUserResponse> {
            client.get("$LIVE_SERVER/userdata/$id") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun getEvents(token: String): Result<GetEventsResponse, DataError.Remote> {
        return safeCall<GetEventsResponse> {
            client.get("$LIVE_SERVER/events") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun getEvent(
        token: String,
        eventId: String
    ): Result<GetMeetingResponse, DataError.Remote> {
        return safeCall<GetMeetingResponse> {
            client.get("$LIVE_SERVER/event/$eventId") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun postAttendance(
        token: String,
        request: AttendanceRequest
    ): Result<AttendanceResponse, DataError.Remote> {
        return safeCall<AttendanceResponse> {
            client.post("$LIVE_SERVER/attendance") {
                bearerAuth(token)
                setBody(request)
            }
        }
    }

    override suspend fun resetPassword(
        token: String,
        request: ResetPasswordRequest
    ): Result<ResetPasswordResponse, DataError.Remote> {
        return safeCall<ResetPasswordResponse> {
            client.post("$LIVE_SERVER/reset_password") {
                bearerAuth(token)
                setBody(request)
            }
        }
    }

    override suspend fun changePassword(
        token: String,
        userId: String,
        request: ChangePasswordRequest
    ): Result<ChangePasswordResponse, DataError.Remote> {
        return safeCall<ChangePasswordResponse> {
            client.post("$LIVE_SERVER/change_password/$userId") {
                bearerAuth(token)
                setBody(request)
            }
        }
    }

    override suspend fun changeUsername(
        token: String,
        userId: String,
        request: ChangeUsernameRequest
    ): Result<ChangeUsernameResponse, DataError.Remote> {
        return safeCall<ChangeUsernameResponse> {
            client.post("$LIVE_SERVER/change_username/$userId") {
                bearerAuth(token)
                setBody(request)
            }
        }
    }

    override suspend fun getAnnouncements(token: String): Result<AnnouncementResponse, DataError.Remote> {
        return safeCall<AnnouncementResponse> {
            client.get("$LIVE_SERVER/announcements") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun getForums(token: String): Result<GetForumsResponse, DataError.Remote> {
        return safeCall<GetForumsResponse> {
            client.get("$LIVE_SERVER/forum") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun postForum(
        token: String,
        request: ForumPostRequest
    ): Result<ForumPostResponse, DataError.Remote> {
        return safeCall<ForumPostResponse> {
            client.post("$LIVE_SERVER/forum") {
                bearerAuth(token)
                setBody(request)
            }
        }
    }

    override suspend fun getForum(
        token: String,
        forumId: String
    ): Result<GetForumResponse, DataError.Remote> {
        return safeCall<GetForumResponse> {
            client.get("$LIVE_SERVER/forum/$forumId") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun postReply(
        token: String,
        request: PostReplyRequest
    ): Result<PostReplyResponse, DataError.Remote> {
        return safeCall<PostReplyResponse> {
            client.post("$LIVE_SERVER/replies") {
                bearerAuth(token)
                setBody(request)
            }
        }
    }

    override suspend fun getRoadMap(token: String): Result<GetRoadMapResponse, DataError.Remote> {
        return safeCall<GetRoadMapResponse> {
            client.get("$LIVE_SERVER/roadmap") {
                bearerAuth(token)
            }
        }
    }

    override suspend fun logout(token: String): Result<LogoutResponse, DataError.Remote> {
        return safeCall<LogoutResponse> {
            client.get("$LIVE_SERVER/logout") {
                bearerAuth(token)
            }
        }
    }

}