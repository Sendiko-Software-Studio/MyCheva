package com.mycheva.app.core.network

import com.mycheva.app.login.data.LoginRequest
import com.mycheva.app.login.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

}