package com.mycheva.app.meeting.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.meeting.detail.domain.DetailMeetingRepositoryImpl
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
class DetailMeetingViewModel @Inject constructor(
    private val repository: DetailMeetingRepositoryImpl
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(DetailMeetingState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DetailMeetingState())

    fun onEvent(event: DetailMeetingAction) {
        when (event) {
            DetailMeetingAction.OnClearState -> clearState()
            is DetailMeetingAction.OnLoadSchedule -> loadSchedule(event.token, event.eventId)
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                notificationMessage = "",
                isLoading = false,
                isRequestFailed = false
            )
        }
    }

    private fun loadSchedule(token: String, eventId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            repository.getEvent(token = "Bearer $token", eventId = eventId)
                .onSuccess { result ->
                    _state.update { it.copy(eventsItem = result.event, isLoading = false) }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestFailed = true,
                            notificationMessage = error.message.toString()
                        )
                    }
                }
        }

}