package com.mycheva.app.schedule.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.schedule.detail.data.GetEventResponse
import com.mycheva.app.schedule.detail.domain.DetailScheduleRepository
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
class DetailScheduleViewModel @Inject constructor(
    private val repository: DetailScheduleRepository
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(DetailScheduleState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DetailScheduleState())

    fun onEvent(event: DetailScheduleEvent) {
        when (event) {
            DetailScheduleEvent.OnClearState -> TODO()
            is DetailScheduleEvent.OnLoadSchedule -> loadSchedule(event.token, event.eventId)
        }
    }

    private fun loadSchedule(token: String, eventId: String) {
        _state.update { it.copy(isLoading = true) }
        val request = repository.getEvent(token = "Bearer $token", eventId = eventId)
        request.enqueue(
            object : Callback<GetEventResponse> {
                override fun onResponse(
                    call: Call<GetEventResponse>,
                    response: Response<GetEventResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> {
                            _state.update { it.copy(
                                eventsItem = response.body()!!.event
                            ) }
                        }

                        else -> _state.update { it.copy(
                            isRequestFailed = true,
                            notificationMessage = "Server error."
                        ) }
                    }
                }

                override fun onFailure(call: Call<GetEventResponse>, throwable: Throwable) {
                    _state.update { it.copy(
                        isRequestFailed = true,
                        notificationMessage = "Server error."
                    ) }
                }

            }
        )
    }

}