package com.mycheva.app.reset_password.presentation

import com.mycheva.app.core.ui.utils.UiText

data class ResetPasswordState(
    val emailText: String = "",
    val isLoading: Boolean = false,
    val isRequestFailed: Boolean = false,
    val notificationMessage: UiText = UiText.DynamicString(""),
    val token: String = "",
    val isRequestSuccess: Boolean = false,
    val userId: String = "",
)
