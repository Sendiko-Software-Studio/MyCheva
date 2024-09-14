package com.mycheva.app.profile.main.presentation

import NOT_FOUND
import SERVER_ERROR
import UNAUTHORIZED
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.profile.main.data.ChangePasswordRequest
import com.mycheva.app.profile.main.data.ChangeUsernameRequest
import com.mycheva.app.profile.main.domain.ProfileRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepositoryImpl
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(ProfileState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            id = userId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileState())

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnClearState -> clearState()
//            ProfileEvent.OnEditProfile -> TODO()
            is ProfileEvent.OnLogout -> logout()
            is ProfileEvent.OnGetProfile -> getProfile(event.token, event.userId)
            is ProfileEvent.OnPasswordEdit -> editPassword(event.password, event.oldPassword)
            is ProfileEvent.OnUsernameEdit -> editUsername(event.username)
            is ProfileEvent.OnPasswordSheetToggle -> passwordSheetToggle(event.isVisible)
            is ProfileEvent.OnUsernameSheetToggle -> usernameSheetToggle(event.isVisible)
            ProfileEvent.OnClearMessage -> clearMessage()
        }
    }

    private fun clearMessage() {
        _state.update { it.copy(notificationMessage = "", isError = false) }
    }

    private fun usernameSheetToggle(visible: Boolean) {
        _state.update {
            it.copy(
                isEditingUsername = visible
            )
        }
    }

    private fun passwordSheetToggle(visible: Boolean) {
        _state.update {
            it.copy(
                isChangingPassword = visible
            )
        }
    }

    private fun editUsername(username: String) {
        _state.update { it.copy(isLoadingUsername = true) }
        val bearerToken = "Bearer ${state.value.token}"
        val request = ChangeUsernameRequest(name = username)
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeUsername(
                token = bearerToken,
                userId = state.value.id,
                request = request
            ).onSuccess { result ->
                Log.i("LOADING_USERNAME", "editUsername: ${state.value.isLoading && !state.value.isEditingUsername}")
                _state.update {
                    it.copy(
                        isLoadingUsername = false,
                        notificationMessage = result,
                        isEditUsernameSuccess = true,
                        isEditingUsername = true
                    )
                }
            }.onFailure { error ->
                when (error.message) {
                    NOT_FOUND -> _state.update {
                        it.copy(isError = true, notificationMessage = "User not found.")
                    }
                    UNAUTHORIZED -> _state.update {
                        it.copy(isError = true, notificationMessage = "Unauthorized, please login again.")
                    }
                    SERVER_ERROR -> _state.update {
                        it.copy(isError = true, notificationMessage = "Server error, try again later.")
                    }
                }
            }
        }
    }

    private fun editPassword(password: String, oldPassword: String) {
        _state.update { it.copy(isLoadingPassword = true) }
        val bearerToken = "Bearer ${state.value.token}"
        val data = ChangePasswordRequest(password, oldPassword)
        viewModelScope.launch(Dispatchers.IO) {
            repository.changePassword(bearerToken, state.value.id, data)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoadingPassword = false,
                            notificationMessage = result,
                            isChangingPasswordSuccess = true
                        )
                    }
                }
                .onFailure { error ->
                    when (error.message) {
                        NOT_FOUND -> _state.update {
                            it.copy(isPasswordNotMatch = true, notificationMessage = "User not found.")
                        }
                        UNAUTHORIZED -> viewModelScope.launch {
                            _state.update {
                                it.copy(isPasswordNotMatch = true, notificationMessage = "Password didn't match.")
                                delay(1000)
                                it.copy(isPasswordNotMatch = true, isLoadingPassword = false)
                            }
                        }

                        SERVER_ERROR -> _state.update {
                            it.copy(isPasswordNotMatch = true, notificationMessage = "Server error, try again later.")
                        }
                    }
                }
        }
    }

    private fun logout() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteToken()
            delay(1000)
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = false,
                    notificationMessage = "Logout success.",
                    isLogoutSuccess = true
                )
            }
        }
    }

    private fun getProfile(token: String, userId: String) {
        _state.update { it.copy(isLoading = true) }
        val bearerToken = "Bearer $token"
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser(bearerToken, userId)
                .onSuccess { user ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            name = user.userDatum.fullName,
                            username = user.name,
                            nim = user.userDatum.nim,
                            faculty = user.userDatum.faculty,
                            major = user.userDatum.major,
                            division = user.userDatum.division.name,
                            imageUrl = user.profileUrl?:"",
                            email = user.userDatum.email
                        )
                    }
                }
                .onFailure { error ->
                    when (error.message) {
                        UNAUTHORIZED -> _state.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                notificationMessage = "Password didn't match."
                            )
                        }

                        NOT_FOUND -> _state.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                notificationMessage = "Account not found."
                            )
                        }

                        SERVER_ERROR -> _state.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                notificationMessage = "",
                isError = false,
                isEditingUsername = false,
                isEditUsernameSuccess = false,
                isChangingPassword = false,
                isChangingPasswordSuccess = false,
            )
        }
    }

}