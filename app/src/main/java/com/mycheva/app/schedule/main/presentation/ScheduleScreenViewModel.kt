package com.mycheva.app.schedule.main.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.dashboard.data.EventsItem
import com.mycheva.app.dashboard.data.GetEventsResponse
import com.mycheva.app.schedule.main.domain.ScheduleScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ScheduleScreenViewModel @Inject constructor(
    private val repository: ScheduleScreenRepository
): ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(ScheduleScreenState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ScheduleScreenState())

    fun onEvent(event: ScheduleScreenEvent) {
        when (event) {
            ScheduleScreenEvent.OnClearState -> _state.update {
                it.copy(
                    isRequestFailed = false,
                    notificationMessage = ""
                )
            }
            is ScheduleScreenEvent.OnLoadSchedule -> loadSchedule(event.token)
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

                            val events = groupItemsByDate(response.body()!!.events)
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
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun groupItemsByDate(items: List<EventsItem>): Map<LocalDate, List<EventsItem>> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return items.groupBy {
            LocalDate.parse(it.date.substring(0, 10), formatter)
        }
    }

}