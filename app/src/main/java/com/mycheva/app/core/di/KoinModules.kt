package com.mycheva.app.core.di

import com.mycheva.app.announcement.data.AnnouncementRepositoryImpl
import com.mycheva.app.announcement.presentation.AnnouncementViewModel
import com.mycheva.app.attendance.data.AttendanceRepositoryImpl
import com.mycheva.app.attendance.presentation.AttendanceViewModel
import com.mycheva.app.bookmark.data.BookmarkRepositoryImpl
import com.mycheva.app.bookmark.presentation.BookmarkViewModel
import com.mycheva.app.core.database.RoomFactory
import com.mycheva.app.core.network.ApiServices
import com.mycheva.app.core.network.HttpClientFactory
import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.preferences.DatastoreFactory
import com.mycheva.app.forum.add.data.AddPostRepositoryImpl
import com.mycheva.app.forum.add.presentation.AddPostForumViewModel
import com.mycheva.app.meeting.detail.data.DetailMeetingRepositoryImpl
import com.mycheva.app.meeting.detail.presentation.DetailMeetingViewModel
import com.mycheva.app.meeting.main.data.MeetingsRepositoryImpl
import com.mycheva.app.meeting.main.presentation.MeetingsViewModel
import com.mycheva.app.profile.main.data.ProfileRepositoryImpl
import com.mycheva.app.profile.main.presentation.ProfileViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.get
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModules = module {
    single<HttpClientEngine> { OkHttp.create() }
    single { HttpClientFactory.create(get()) }
    single { RoomFactory.create(androidContext()) }
    single { RoomFactory.create(androidContext()).bookmarkDao }
    single { DatastoreFactory.create(androidContext()) }
    single { KtorClient(get()) }

    singleOf(::KtorClient).bind<ApiServices>()
    singleOf(::AnnouncementRepositoryImpl)
    singleOf(::AttendanceRepositoryImpl)
    singleOf(::BookmarkRepositoryImpl)
    singleOf(::ProfileRepositoryImpl)
    singleOf(::MeetingsRepositoryImpl)
    singleOf(::DetailMeetingRepositoryImpl)
    singleOf(::AddPostRepositoryImpl)

    factory { AnnouncementViewModel(get()) }
    factory { AttendanceViewModel(get()) }
    factory { BookmarkViewModel(get()) }
    factory { ProfileViewModel(get()) }
    factory { MeetingsViewModel(get()) }
    factory { DetailMeetingViewModel(get()) }
    factory { AddPostForumViewModel(get()) }
}