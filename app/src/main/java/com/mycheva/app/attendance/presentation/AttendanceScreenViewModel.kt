package com.mycheva.app.attendance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.attendance.data.AttendanceRequest
import com.mycheva.app.attendance.data.AttendanceResponse
import com.mycheva.app.attendance.domain.AttendanceRepository
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
class AttendanceScreenViewModel @Inject constructor(
    private val repository: AttendanceRepository
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
            AttendanceScreenEvent.OnClearState -> _state.update {
                it.copy(
                    isRequestSuccess = false,
                    isRequestFailed = false,
                    notificationMessage = ""
                )
            }
            is AttendanceScreenEvent.OnEventIdRead -> postAttendance(event.token, event.eventId, event.userId)
        }
    }

    private fun postAttendance(token: String, eventId: String, userId: String) {
        _state.update { it.copy(isLoading = true) }
        val data = AttendanceRequest(
            eventId = eventId,
            userId = userId,
            status = "present"
        )
        val request = repository.postAttendance(
            token = "Bearer $token",
            request = data
        )
        request.enqueue(
            object : Callback<AttendanceResponse> {
                override fun onResponse(
                    call: Call<AttendanceResponse>,
                    response: Response<AttendanceResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        201 -> _state.update {
                            it.copy(
                                notificationMessage = response.body()!!.message,
                                isRequestSuccess = true
                            )
                        }

                        else -> _state.update {
                            it.copy(
                                notificationMessage = "Server error.",
                                isRequestSuccess = false
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<AttendanceResponse>, throwable: Throwable) {
                    _state.update {
                        it.copy(
                            notificationMessage = "Server error.",
                            isRequestSuccess = false
                        )
                    }
                }

            }
        )
    }

}