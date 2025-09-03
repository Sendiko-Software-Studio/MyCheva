package com.mycheva.app.login.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(

    @SerialName("password")
    val password: String,

    @SerialName("name")
    val name: String,
)
