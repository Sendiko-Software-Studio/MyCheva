package com.mycheva.app.profile.edit_username.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.profile.edit_username.data.ChangeUsernameRequest
import com.mycheva.app.profile.edit_username.data.ChangeUsernameResponse
import com.mycheva.app.profile.edit_username.domain.EditUsernameRepository
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
class EditUsernameScreenViewModel @Inject constructor(
    private val repository: EditUsernameRepository
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(EditUsernameScreenState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(token = token, userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EditUsernameScreenState())

    fun onEvent(event: EditUsernameScreenEvent) {
        when (event) {
            is EditUsernameScreenEvent.OnUsernameChanged -> onUsernameChanged(event.value)
            EditUsernameScreenEvent.OnUsernameCleared -> onUsernameCleared()
            is EditUsernameScreenEvent.OnSave -> changeUsername(
                event.token,
                event.userId,
                event.name
            )
            EditUsernameScreenEvent.OnClearState -> _state.update { it.copy(
                isLoading = false,
                isRequestFailed = false,
                isRequestSuccess = false,
                notificationMessage = ""
            ) }
        }
    }

    private fun changeUsername(token: String, userId: String, name: String) {
        if (name.isBlank()){
            _state.update { it.copy(isRequestFailed = true, notificationMessage = "Username can't be empty.") }
            return
        }
        _state.update { it.copy(isLoading = true) }
        val data = ChangeUsernameRequest(name = name)
        val request = repository.changeUsername("Bearer $token", userId, data)
        request.enqueue(
            object : Callback<ChangeUsernameResponse> {
                override fun onResponse(
                    call: Call<ChangeUsernameResponse>,
                    response: Response<ChangeUsernameResponse>
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
                                isRequestFailed = true,
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }

                override fun onFailure(p0: Call<ChangeUsernameResponse>, p1: Throwable) {
                    _state.update {
                        it.copy(
                            isRequestFailed = true,
                            notificationMessage = "Server error."
                        )
                    }
                }

            }
        )
    }

    private fun onUsernameCleared() {
        _state.update { it.copy(usernameText = "") }
    }

    private fun onUsernameChanged(value: String) {
        _state.update { it.copy(usernameText = value) }
    }

}