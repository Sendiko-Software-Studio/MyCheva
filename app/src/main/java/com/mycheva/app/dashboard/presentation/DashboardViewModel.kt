package com.mycheva.app.dashboard.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.dashboard.domain.DashboardRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: DashboardRepositoryImpl
) : ViewModel() {

    private val _token = repo.getToken()
    private val _userId = repo.getUserId()
    private val _state = MutableStateFlow(DashboardState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            userId = userId,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardState())

    init {
        _state.update { it.copy(isLoading = true) }
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.OnClearState -> _state.update {
                it.copy(
                    isLoading = false,
                    isRequestFailed = false,
                    notificationMessage = ""
                )
            }

            is DashboardEvent.GetUserData -> getUserData(event.token, event.userId)
            is DashboardEvent.GetEventData -> getEventData(event.token)
        }
    }

    private fun getEventData(token: String) {
        _state.update { it.copy(isLoading = true) }
        Log.i("GET_EVENT", "isLoading, outside viewModelScope: ${state.value.isLoading}")
        viewModelScope.launch {
            repo.getEvents(token)
                .onSuccess { result ->
                    Log.i("GET_EVENT", "getEventData: $result")
                    Log.i("GET_EVENT", "isLoading: ${state.value.isLoading}")
                    val event = result.first {
                        it.divisionId.toString() == state.value.divisionId
                    }
                    _state.update {
                        it.copy(
                            latestEvent = event,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isRequestFailed = true,
                            isLoading = false,
                            notificationMessage = error.message ?: "Server error."
                        )
                    }
                }
        }
    }

    private fun getUserData(token: String, userId: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repo.getUser(token, userId)
                .onSuccess { user ->
                    Log.i("GET_USER", "getUserData: $user")
                    Log.i("GET_USER", "isLoading: ${state.value.isLoading}")
                    _state.update {
                        it.copy(
                            name = user.name,
                            isLoading = false,
                            divisionId = user.userDatum.divisionId.toString()
                        )
                    }
                }.onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestFailed = true,
                            notificationMessage = error.message ?: "Server error."
                        )
                    }
                }
        }
    }

}