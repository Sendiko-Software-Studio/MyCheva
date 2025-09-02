package com.mycheva.app.login.data.dto

import kotlinx.serialization.SerialName

data class LoginRequest(

    @SerialName("password")
    val password: String,

    @SerialName("name")
    val name: String,
)
