package com.mycheva.app.profile.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.DataError.Remote.NOT_FOUND
import com.mycheva.app.core.network.utils.DataError.Remote.UNAUTHORIZED
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.profile.main.domain.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
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
        _state.update { it.copy(notificationMessage = UiText.DynamicString(""), isError = false) }
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
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeUsername(
                token = state.value.token,
                userId = state.value.id,
                username = username,
            ).onSuccess { result ->
                _state.update {
                    it.copy(
                        isLoadingUsername = false,
                        notificationMessage = UiText.DynamicString(result),
                        isEditUsernameSuccess = true,
                        isEditingUsername = true
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(isError = true, notificationMessage = error.asUiText(), isLoading = false)
                }
            }
        }
    }

    private fun editPassword(password: String, oldPassword: String) {
        _state.update { it.copy(isLoadingPassword = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.changePassword(state.value.token, state.value.id, password, oldPassword)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoadingPassword = false,
                            notificationMessage = UiText.DynamicString(result),
                            isChangingPasswordSuccess = true
                        )
                    }
                }
                .onError { error ->
                    when (error) {
                        UNAUTHORIZED -> _state.update {
                            it.copy(isPasswordNotMatch = true, isError = true, notificationMessage = UiText.DynamicString("Password not matched."))
                        }
                        else -> _state.update {
                            it.copy(isError = true, notificationMessage = error.asUiText())
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
                    notificationMessage = UiText.DynamicString("Logout success."),
                    isLogoutSuccess = true
                )
            }
        }
    }

    private fun getProfile(token: String, userId: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser(token, userId)
                .onSuccess { user ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            name = user.data.fullName,
                            username = user.name,
                            nim = user.data.nim,
                            faculty = user.data.faculty,
                            major = user.data.major,
                            division = user.data.division.name,
                            imageUrl = user.profileUrl,
                            email = user.data.email
                        )
                    }
                }
                .onError { error ->
                    when (error) {
                        NOT_FOUND -> _state.update {
                            it.copy(isError = true, isLoading = false, notificationMessage = UiText.DynamicString("User not found."))
                        }
                        else -> _state.update {
                            it.copy(isError = true, isLoading = false, notificationMessage = error.asUiText())
                        }
                    }
                }
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                notificationMessage = UiText.DynamicString(""),
                isError = false,
                isEditingUsername = false,
                isEditUsernameSuccess = false,
                isChangingPassword = false,
                isChangingPasswordSuccess = false,
            )
        }
    }

}