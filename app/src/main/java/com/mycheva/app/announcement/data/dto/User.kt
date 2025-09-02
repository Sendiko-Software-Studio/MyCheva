package com.mycheva.app.announcement.data.dto

import kotlinx.serialization.SerialName


data class User(

    @SerialName("profileUrl")
    val profileUrl: String?,

    @SerialName("password")
    val password: String,

    @SerialName("roleId")
    val roleId: Int,

    @SerialName("name")
    val name: String,

    @SerialName("id")
    val id: Int,
)
