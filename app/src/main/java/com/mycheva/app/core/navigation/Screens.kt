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
data object ScheduleScreen
