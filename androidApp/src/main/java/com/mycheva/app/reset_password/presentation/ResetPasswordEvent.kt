package com.mycheva.app.reset_password.presentation

sealed class ResetPasswordEvent {
    data class OnEmailTextChange(val email: String): ResetPasswordEvent()
    data class OnResetPassword(val token: String, val email: String): ResetPasswordEvent()
    data object OnEmailCleared: ResetPasswordEvent()
    data object ClearState: ResetPasswordEvent()
}
