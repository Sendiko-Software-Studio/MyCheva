package com.mycheva.app.profile.edit_pass.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.profile.edit_pass.data.ChangePasswordRequest
import com.mycheva.app.profile.edit_pass.data.ChangePasswordResponse
import com.mycheva.app.profile.edit_pass.domain.EditPasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EditPasswordScreenViewModel @Inject constructor(
    private val repository: EditPasswordRepository
): ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(EditPasswordScreenState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(token = token, userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EditPasswordScreenState())

    fun onEvent(event: EditPasswordScreenEvent) {
        when (event) {
            is EditPasswordScreenEvent.OnNewPassChanged -> onNewPassChanged(event.value)
            is EditPasswordScreenEvent.OnOldPassChanged -> onOldPassChanged(event.value)
            EditPasswordScreenEvent.OnNewPassCleared -> onNewPassCleared()
            EditPasswordScreenEvent.OnOldPassCleared -> onOldPassCleared()
            is EditPasswordScreenEvent.OnSave -> changePassword(event.token, event.userId, event.newPass)
            EditPasswordScreenEvent.OnClearState -> _state.update { it.copy(
                isLoading = false,
                isRequestError = false,
                isRequestSuccess = false,
                notificationMessage = ""
            ) }
        }
    }

    private fun changePassword(token: String, userId: String, newPass: String) {
        _state.update { it.copy(isLoading = true) }
        val data = ChangePasswordRequest(password = newPass)
        val request = repository.changePassword(
            token = "Bearer $token",
            userId = userId,
            request = data
        )

        request.enqueue(
            object : Callback<ChangePasswordResponse> {
                override fun onResponse(
                    call: Call<ChangePasswordResponse>,
                    response: Response<ChangePasswordResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> _state.update {
                            it.copy(
                                isRequestSuccess = true,
                                notificationMessage = response.body()!!.message
                            )
                        }

                        else -> _state.update {
                            it.copy(
                                isRequestError = true,
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }

                override fun onFailure(p0: Call<ChangePasswordResponse>, p1: Throwable) {
                    _state.update {
                        it.copy(
                            isRequestError = true,
                            notificationMessage = "Server error."
                        )
                    }
                }

            }
        )
    }

    private fun onOldPassCleared() {
        _state.update {
            it.copy(oldPassText = "")
        }
    }

    private fun onNewPassCleared() {
        _state.update {
            it.copy(newPassText = "")
        }
    }

    private fun onNewPassChanged(value: String) {
        _state.update {
            it.copy(newPassText = value)
        }
    }

    private fun onOldPassChanged(value: String) {
        _state.update {
            it.copy(oldPassText = value)
        }
    }

}