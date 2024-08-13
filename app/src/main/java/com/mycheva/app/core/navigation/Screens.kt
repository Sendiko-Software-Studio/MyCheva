package com.mycheva.app.core.navigation

import kotlinx.serialization.Serializable

/*
* Class untuk menentukan Route untuk Screen.
* */

@Serializable
data object AuthGraph

@Serializable
data object MainGraph

@Serializable
data object DetailsGraph

@Serializable
data object SplashScreen

@Serializable
data object LoginScreen

@Serializable
data object ResetPasswordScreen

@Serializable
data object DashboardScreen

@Serializable
data object AttendanceScreen

@Serializable
data object ProfileScreen

@Serializable
data object EditUsernameScreen

@Serializable
data object EditPasswordScreen

@Serializable
data object ScheduleScreen

@Serializable
data class DetailSchedule(
    val eventId: String,
)

@Serializable
data object AnnouncementScreen

@Serializable
data object BookmarkScreen

@Serializable
data object ForumScreen

@Serializable
data class CommentScreen(
    val forumId: String,
)

@Serializable
data object RoadmapScreen
