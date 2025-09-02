package com.mycheva.app.core.di

import com.mycheva.app.announcement.data.AnnouncementRepositoryImpl
import com.mycheva.app.announcement.domain.AnnouncementRepository
import com.mycheva.app.announcement.presentation.AnnouncementViewModel
import com.mycheva.app.attendance.data.AttendanceRepositoryImpl
import com.mycheva.app.attendance.domain.AttendanceRepository
import com.mycheva.app.attendance.presentation.AttendanceViewModel
import com.mycheva.app.bookmark.data.BookmarkRepositoryImpl
import com.mycheva.app.bookmark.domain.BookmarkRepository
import com.mycheva.app.bookmark.presentation.BookmarkViewModel
import com.mycheva.app.core.database.RoomFactory
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.HttpClientFactory
import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.core.preferences.DatastoreFactory
import com.mycheva.app.dashboard.data.DashboardRepositoryImpl
import com.mycheva.app.dashboard.domain.DashboardRepository
import com.mycheva.app.dashboard.presentation.DashboardViewModel
import com.mycheva.app.forum.add.data.AddPostRepositoryImpl
import com.mycheva.app.forum.add.domain.AddPostRepository
import com.mycheva.app.forum.add.presentation.AddPostForumViewModel
import com.mycheva.app.forum.main.data.ForumRepositoryImpl
import com.mycheva.app.forum.main.domain.ForumRepository
import com.mycheva.app.forum.main.presentation.ForumViewModel
import com.mycheva.app.forum.replies.data.RepliesRepositoryImpl
import com.mycheva.app.forum.replies.domain.RepliesRepository
import com.mycheva.app.forum.replies.presentation.RepliesViewModel
import com.mycheva.app.login.data.LoginRepositoryImpl
import com.mycheva.app.login.domain.LoginRepository
import com.mycheva.app.login.presentation.LoginViewModel
import com.mycheva.app.meeting.detail.data.DetailMeetingRepositoryImpl
import com.mycheva.app.meeting.detail.domain.DetailMeetingRepository
import com.mycheva.app.meeting.detail.presentation.DetailMeetingViewModel
import com.mycheva.app.meeting.main.data.MeetingsRepositoryImpl
import com.mycheva.app.meeting.main.domain.MeetingsRepository
import com.mycheva.app.meeting.main.presentation.MeetingsViewModel
import com.mycheva.app.profile.main.data.ProfileRepositoryImpl
import com.mycheva.app.profile.main.domain.ProfileRepository
import com.mycheva.app.profile.main.presentation.ProfileViewModel
import com.mycheva.app.reset_password.data.ResetPasswordRepositoryImpl
import com.mycheva.app.reset_password.domain.ResetPasswordRepository
import com.mycheva.app.reset_password.presentation.ResetPasswordViewModel
import com.mycheva.app.roadmap.data.RoadMapRepositoryImpl
import com.mycheva.app.roadmap.domain.RoadMapRepository
import com.mycheva.app.roadmap.presentation.RoadMapViewModel
import com.mycheva.app.splashscreen.data.SplashScreenRepository
import com.mycheva.app.splashscreen.presentation.SplashScreenViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModules = module {
    single<HttpClientEngine> { OkHttp.create() }
    single { HttpClientFactory.create(get()) }
    single { RoomFactory.create(androidContext()) }
    single { RoomFactory.create(androidContext()).bookmarkDao }
    single { DatastoreFactory.create(androidContext()) }
    single { AppPreferences(get()) }
    single { KtorClient(get()) }

    singleOf(::KtorClient).bind<ApiServices>()
    singleOf(::AnnouncementRepositoryImpl).bind<AnnouncementRepository>()
    singleOf(::AttendanceRepositoryImpl).bind<AttendanceRepository>()
    singleOf(::BookmarkRepositoryImpl).bind<BookmarkRepository>()
    singleOf(::ProfileRepositoryImpl).bind<ProfileRepository>()
    singleOf(::MeetingsRepositoryImpl).bind<MeetingsRepository>()
    singleOf(::DetailMeetingRepositoryImpl).bind<DetailMeetingRepository>()
    singleOf(::AddPostRepositoryImpl).bind<AddPostRepository>()
    singleOf(::RepliesRepositoryImpl).bind<RepliesRepository>()
    singleOf(::DashboardRepositoryImpl).bind<DashboardRepository>()
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
    singleOf(::ResetPasswordRepositoryImpl).bind<ResetPasswordRepository>()
    singleOf(::RoadMapRepositoryImpl).bind<RoadMapRepository>()
    singleOf(::ForumRepositoryImpl).bind<ForumRepository>()
    singleOf(::SplashScreenRepository)

    factory { AnnouncementViewModel(get()) }
    factory { AttendanceViewModel(get()) }
    factory { BookmarkViewModel(get()) }
    factory { ProfileViewModel(get()) }
    factory { MeetingsViewModel(get()) }
    factory { DetailMeetingViewModel(get()) }
    factory { AddPostForumViewModel(get()) }
    factory { RepliesViewModel(get()) }
    factory { DashboardViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { ResetPasswordViewModel(get()) }
    factory { RoadMapViewModel(get()) }
    factory { ForumViewModel(get()) }
    factory { SplashScreenViewModel(get()) }
}