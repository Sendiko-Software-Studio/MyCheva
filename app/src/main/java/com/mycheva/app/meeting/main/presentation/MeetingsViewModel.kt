package com.mycheva.app.meeting.main.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.data.GetEventsResponse
import com.mycheva.app.meeting.main.domain.MeetingsRepository
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
class MeetingsViewModel @Inject constructor(
    private val repository: MeetingsRepository
): ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(MeetingsState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MeetingsState())

    fun onEvent(action: MeetingsAction) {
        when (action) {
            MeetingsAction.OnClearState -> clearState()
            is MeetingsAction.OnLoadSchedule -> loadSchedule(action.token)
            is MeetingsAction.OnSearchTextChanged -> searchMeetings(action.value)
            MeetingsAction.OnClearFilter -> loadSchedule(state.value.token)
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isRequestFailed = false,
                notificationMessage = ""
            )
        }
    }

    private fun searchMeetings(value: String) {
        _state.update { it.copy(searchText = value) }
        val searched = state.value.events.filter {
            it.name.contains(value) || it.desc.contains(value)
        }
        _state.update {
            it.copy(
                events = searched
            )
        }
    }

    private fun loadSchedule(token: String) {
        _state.update { it.copy(isLoading = true) }
        val request = repository.getEvents("Bearer $token")
        request.enqueue(
            object : Callback<GetEventsResponse> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(
                    call: Call<GetEventsResponse>,
                    response: Response<GetEventsResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                       200 -> {
                            val events = response.body()!!.events
                           _state.update {
                               it.copy(
                                   events = events
                               )
                           }
                       }

                        else -> _state.update {
                            it.copy(isRequestFailed = true, notificationMessage = "Server error.")
                        }
                    }
                }

                override fun onFailure(call: Call<GetEventsResponse>, throwable: Throwable) {
                    _state.update {
                        it.copy(isRequestFailed = true, notificationMessage = "Server error.")
                    }
                }

            }
        )
        _state.update {
            it.copy(searchText = "")
        }
    }
}