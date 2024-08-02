package com.mycheva.app.login.domain

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepositoryImpl
) {

    suspend operator fun invoke(username: String, password: String) {
        loginRepository.login(username, password)
    }

    fun verifyForm(username: String, password: String): Boolean {
        return loginRepository.verifyForm(username, password)
    }

}