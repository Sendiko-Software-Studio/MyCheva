package com.mycheva.app.attendance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.domain.AttendanceRepositoryImpl
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
class AttendanceScreenViewModel @Inject constructor(
    private val repository: AttendanceRepositoryImpl
): ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(AttendanceScreenState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            userId = userId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AttendanceScreenState())

    fun onEvent(event: AttendanceScreenEvent) {
        when (event) {
            AttendanceScreenEvent.OnClearState -> clearState()
            is AttendanceScreenEvent.OnEventIdRead -> postAttendance(event.token, event.eventId, event.userId)
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isRequestSuccess = false,
                isRequestFailed = false,
                notificationMessage = ""
            )
        }
    }

    private fun postAttendance(token: String, eventId: String, userId: String) = viewModelScope.launch(Dispatchers.IO) {
        if (state.value.isScanning) {
            _state.update { it.copy(notificationMessage = "Attendance successfully recorded.") }
            return@launch
        }
        _state.update { it.copy(isLoading = true, isScanning = true) }
        val data = AttendanceRequest(
            eventId = eventId,
            userId = userId,
            status = "present"
        )
        repository.postAttendance(
            token = "Bearer $token",
            request = data
        )
            .onSuccess {
                _state.update {
                    it.copy(isLoading = false, notificationMessage = "Attendance successfully recorded.", isRequestSuccess = true)
                }
            }
            .onFailure {
                _state.update {
                    it.copy(isLoading = false, notificationMessage = "Failed to record attendance.", isRequestFailed = true)
                }
            }
    }

}