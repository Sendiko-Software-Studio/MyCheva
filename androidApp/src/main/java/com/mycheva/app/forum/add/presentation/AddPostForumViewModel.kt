package com.mycheva.app.forum.add.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.forum.add.domain.AddPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddPostForumViewModel(
    private val repository: AddPostRepository,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(AddPostForumState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(token = token, userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AddPostForumState())

    fun onEvent(event: AddPostForumEvent) {
        when (event) {
            AddPostForumEvent.OnClearState -> clearState()
            is AddPostForumEvent.OnPostTextChanged -> changePostText(event.text)
            is AddPostForumEvent.OnPost -> post(event.token, event.userId, event.content)
        }
    }

    private fun changePostText(value: String) {
        _state.update {
            it.copy(
                postText = value
            )
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                isRequestError = false,
                isRequestSuccess = false,
                notificationMessage = UiText.DynamicString("")
            )
        }
    }

    private fun post(token: String, userId: String, content: String) =
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value.postText.isBlank()) {
                _state.update {
                    it.copy(
                        isRequestError = true,
                        notificationMessage = UiText.DynamicString("Post can't be empty.")
                    )
                }
                return@launch
            }
            _state.update { it.copy(isLoading = true) }
            repository.postForum(token, userId, content)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestSuccess = true,
                            notificationMessage = UiText.DynamicString(result)
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestError = true,
                            notificationMessage = error.asUiText()
                        )
                    }
                }

        }

}