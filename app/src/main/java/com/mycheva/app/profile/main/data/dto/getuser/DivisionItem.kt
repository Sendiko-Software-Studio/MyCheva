package com.mycheva.app.profile.main.data.dto.getuser

import com.google.gson.annotations.SerializedName

data class DivisionItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
