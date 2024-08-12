package com.mycheva.app.reset_password.presentation

sealed class ResetPasswordScreenEvent {
    data class OnEmailTextChange(val email: String): ResetPasswordScreenEvent()
    data class OnResetPassword(val token: String, val email: String): ResetPasswordScreenEvent()
    data object OnEmailCleared: ResetPasswordScreenEvent()
    data object ClearState: ResetPasswordScreenEvent()
}
