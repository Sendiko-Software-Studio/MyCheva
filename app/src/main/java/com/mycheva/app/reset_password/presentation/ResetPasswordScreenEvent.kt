package com.mycheva.app.reset_password.presentation

sealed class ResetPasswordScreenEvent {
    data class OnEmailTextChange(val email: String): ResetPasswordScreenEvent()
    data object OnResetPassword: ResetPasswordScreenEvent()
    data object OnEmailCleared: ResetPasswordScreenEvent()
}
