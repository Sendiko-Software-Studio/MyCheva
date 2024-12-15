package com.mycheva.app.meeting.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.meeting.main.domain.MeetingsRepository
import com.mycheva.app.meeting.main.domain.MeetingsRepositoryImpl
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
class MeetingsViewModel @Inject constructor(
    private val repository: MeetingsRepositoryImpl
) : ViewModel() {

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

    private fun loadSchedule(token: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.update { it.copy(isLoading = true) }
        repository.getEvents("Bearer $token")
            .onSuccess { result ->
                _state.update { it.copy(events = result.events, isLoading = false) }
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

        _state.update {
            it.copy(searchText = "")
        }
    }
}