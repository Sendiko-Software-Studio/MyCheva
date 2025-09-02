package com.mycheva.app.meeting.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.meeting.detail.domain.DetailMeetingRepository
import com.mycheva.app.meeting.main.presentation.MeetingUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailMeetingViewModel(
    private val repository: DetailMeetingRepository,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(DetailMeetingState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DetailMeetingState())

    fun onEvent(event: DetailMeetingEvent) {
        when (event) {
            DetailMeetingEvent.OnClearState -> clearState()
            is DetailMeetingEvent.OnLoadSchedule -> loadMeeting(event.token, event.eventId)
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                notificationMessage = UiText.DynamicString(""),
                isLoading = false,
                isRequestFailed = false
            )
        }
    }

    private fun loadMeeting(token: String, meetingId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            repository.getMeeting(token = token, meetingId = meetingId)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            meeting = MeetingUi.fromDomain(result),
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestFailed = true,
                            notificationMessage = error.asUiText()
                        )
                    }
                }
        }

}