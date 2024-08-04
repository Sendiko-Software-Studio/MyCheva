package com.mycheva.app.profile.edit_pass

data class EditPasswordScreenState(
    val oldPassText: String = "",
    val newPassText: String = "",
    val isLoading: Boolean = false,
    val notificationMessage: String = ""
)
