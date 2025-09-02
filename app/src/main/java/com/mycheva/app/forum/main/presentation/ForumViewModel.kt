package com.mycheva.app.forum.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.forum.main.domain.ForumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForumViewModel(
    private val repository: ForumRepository,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(ForumState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ForumState())

    fun onEvent(event: ForumEvent) {
        when (event) {
            ForumEvent.OnClearNotification -> clearNotification()
            is ForumEvent.OnLoadForums -> loadForums(event.token)
            ForumEvent.OnClearFilter -> clearFilter()
            is ForumEvent.OnSearchTextChange -> searchForums(event.value)
        }
    }

    private fun clearFilter() {
        loadForums(state.value.token)
        _state.update {
            it.copy(
                searchText = "",
            )
        }
    }

    private fun clearNotification() {
        _state.update {
            it.copy(
                isLoading = false,
                isRequestError = false,
                notificationMessage = UiText.DynamicString("")
            )
        }
    }

    private fun searchForums(value: String) {
        _state.update { it.copy(searchText = value) }
        val searched = state.value.forums.filter { forumsItem ->
            forumsItem.user.name.contains(value) || forumsItem.content.contains(value)
        }
        _state.update {
            it.copy(
                forums = searched
            )
        }
    }

    private fun loadForums(token: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        repository.getForums(token)
            .onSuccess { result ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        forums = result.reversed()
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        notificationMessage = error.asUiText(),
                        isRequestError = true,
                        isLoading = false
                    )
                }
            }
    }

}