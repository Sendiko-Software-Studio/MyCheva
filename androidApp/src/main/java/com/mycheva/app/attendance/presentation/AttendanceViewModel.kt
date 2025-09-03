package com.mycheva.app.attendance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.attendance.data.AttendanceRepositoryImpl
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AttendanceViewModel(
    private val repository: AttendanceRepositoryImpl,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(AttendanceState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            userId = userId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AttendanceState())

    fun onEvent(event: AttendanceEvent) {
        when (event) {
            AttendanceEvent.OnClearState -> clearState()
            is AttendanceEvent.OnEventIdRead -> postAttendance(
                event.token,
                event.eventId,
                event.userId
            )
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isRequestSuccess = false,
                isRequestFailed = false,
                notificationMessage = UiText.DynamicString("")
            )
        }
    }

    private fun postAttendance(token: String, eventId: String, userId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            if (state.value.isScanning) {
                _state.update { it.copy(notificationMessage = UiText.DynamicString("Attendance successfully recorded.")) }
                return@launch
            }
            _state.update { it.copy(isLoading = true, isScanning = true) }
            repository.postAttendance(
                token = token,
                eventId = eventId,
                userId = userId,
            ).onSuccess { response ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isScanning = false,
                        isRequestSuccess = true,
                        notificationMessage = UiText.DynamicString(response.message)
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isScanning = false,
                        notificationMessage = error.asUiText()
                    )
                }
            }
        }

}