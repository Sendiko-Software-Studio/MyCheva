package com.mycheva.app.schedule.core

import com.google.gson.annotations.SerializedName

data class EventsItem(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("details")
    val details: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("divisionId")
    val divisionId: Int,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("desc")
    val desc: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
)

