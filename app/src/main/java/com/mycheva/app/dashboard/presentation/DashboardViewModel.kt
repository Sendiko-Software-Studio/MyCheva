package com.mycheva.app.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.dashboard.domain.DashboardRepository
import com.mycheva.app.meeting.core.domain.Meeting
import com.mycheva.app.meeting.main.presentation.MeetingUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class DashboardViewModel(
    private val repository: DashboardRepository,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(DashboardState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            userId = userId,
        )
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            DashboardState()
        )

    init {
        _state.update { it.copy(isLoading = true) }
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.OnClearState -> clearState()
            is DashboardEvent.GetUserData -> getUserData(event.token, event.userId)
            is DashboardEvent.GetEventData -> getEventData(event.token)
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                isRequestFailed = false,
                notificationMessage = UiText.DynamicString("")
            )
        }
    }

    fun filterEvents(events: List<Meeting>, userDivisionId: Int): List<Meeting> {
        val today = LocalDate.now()
        val nextWeek = today.plusDays(7)

        val filteredEvents = events.filter { event ->
            event.divisionId == userDivisionId &&
                    LocalDate.parse(event.date.substringBefore("T")) in today..nextWeek
        }
        return filteredEvents.ifEmpty { emptyList() }
    }

    private fun getEventData(token: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.getEvents(token)
                .onSuccess { result ->
                    val meeting =
                        if (filterEvents(result, _state.value.divisionId.toInt()).isNotEmpty())
                            filterEvents(result, _state.value.divisionId.toInt()).first()
                        else null
                    _state.update {
                        it.copy(
                            latestEvent = if (meeting != null) MeetingUi.fromDomain(meeting) else null,
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isRequestFailed = true,
                            isLoading = false,
                            notificationMessage = error.asUiText()
                        )
                    }
                }
        }
    }

    private fun getUserData(token: String, userId: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser(token, userId)
                .onSuccess { user ->
                    _state.update {
                        it.copy(
                            name = user.name,
                            isLoading = false,
                            divisionId = user.data.division.id.toString()
                        )
                    }
                }.onError { error ->
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

}