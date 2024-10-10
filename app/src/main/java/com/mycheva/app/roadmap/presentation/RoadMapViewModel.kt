package com.mycheva.app.roadmap.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.roadmap.domain.RoadMapRepositoryImpl
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
class RoadMapViewModel @Inject constructor(
    private val repository: RoadMapRepositoryImpl
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
            repository.getRoadMaps("Bearer $token")
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            roadMaps = result.roadmaps,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isRequestFailed = true,
                            notificationMessage = error.message.toString(),
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