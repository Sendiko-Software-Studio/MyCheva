package com.mycheva.app.roadmap.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.roadmap.domain.RoadMapRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoadMapViewModel(
    private val repository: RoadMapRepository,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(RoadMapState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RoadMapState())

    fun onEvent(event: RoadMapEvent) {
        when (event) {
            RoadMapEvent.OnClearState -> clearState()
            is RoadMapEvent.OnLoadData -> loadData(event.token, event.divisionId)
        }
    }

    private fun loadData(token: String, divisionId: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRoadMaps(token)
                .onSuccess { result ->
                    _state.update { state ->
                        state.copy(
                            roadMaps = result.filter { roadmaps ->
                                roadmaps.divisionId.toString() == divisionId
                            }.map { RoadMapUi.fromDomain(it) },
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isRequestFailed = true,
                            notificationMessage = error.asUiText(),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun clearState() {
        _state.update { RoadMapState() }
    }
}