package com.mycheva.app.core.network

import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse
import com.mycheva.app.profile.main.data.GetUserResponse
import com.mycheva.app.profile.main.data.LogoutResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServices {

    @POST("login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @GET("userdata")
    fun getUser(
        @Header("Authorization") token: String,
    ): Call<GetUserResponse>

    @GET("logout")
    fun logout(
        @Header("Authorization") token: String,
    ): Call<LogoutResponse>

}