package com.mycheva.app.meeting.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.meeting.main.domain.MeetingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MeetingsViewModel(
    private val repository: MeetingsRepository,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(MeetingsState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MeetingsState())

    fun onEvent(action: MeetingEvent) {
        when (action) {
            MeetingEvent.OnClearState -> clearState()
            is MeetingEvent.OnLoadSchedule -> loadSchedule(action.token)
            is MeetingEvent.OnSearchTextChanged -> searchMeetings(action.value)
            MeetingEvent.OnClearFilter -> loadSchedule(state.value.token)
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isRequestFailed = false,
                notificationMessage = UiText.DynamicString("")
            )
        }
    }

    private fun searchMeetings(value: String) {
        _state.update { it.copy(searchText = value) }
        val searched = state.value.meetings.filter {
            it.name.contains(value) || it.place.contains(value)
        }
        _state.update {
            it.copy(
                meetings = searched
            )
        }
    }

    private fun loadSchedule(token: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.update { it.copy(isLoading = true) }
        repository.getEvents(token)
            .onSuccess { result ->
                _state.update { state ->
                    state.copy(
                        meetings = result.map { MeetingUi.fromDomain(it) },
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

        _state.update {
            it.copy(searchText = "")
        }
    }
}