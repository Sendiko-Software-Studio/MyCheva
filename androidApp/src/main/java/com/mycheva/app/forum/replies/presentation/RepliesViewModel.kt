package com.mycheva.app.forum.replies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import com.mycheva.app.forum.replies.domain.RepliesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepliesViewModel(
    private val repository: RepliesRepository,
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(RepliesState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            userId = userId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RepliesState())

    fun onEvent(event: RepliesEvent) {
        when (event) {
            is RepliesEvent.OnRepliesTextChange -> changeCommentText(event.value)

            RepliesEvent.OnClearState -> clearState()

            is RepliesEvent.OnLoadData -> loadData(event.token, event.forumId)
            is RepliesEvent.OnPostReplies -> postReply(event.token, event.userId, event.forumId)
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                notificationMessage = UiText.DynamicString(""),
                isReplyPosted = false,
                commentText = ""
            )
        }
    }

    private fun changeCommentText(value: String) {
        _state.update {
            it.copy(commentText = value)
        }
    }

    private fun postReply(token: String, userId: String, forumId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value.commentText.isBlank()) {
                _state.update {
                    it.copy(
                        isError = true,
                        notificationMessage = UiText.DynamicString("Comment can't be empty.")
                    )
                }
                return@launch
            }
            _state.update { it.copy(isLoading = true) }
            repository.postReply(token = token, userId, forumId, state.value.commentText)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isReplyPosted = true,
                            notificationMessage = UiText.DynamicString(result)
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            notificationMessage = error.asUiText()
                        )
                    }
                }

        }

    private fun loadData(token: String, forumId: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.update { it.copy(isLoading = true) }
        repository.loadData(token, forumId)
            .onSuccess { result ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        replies = result.replies,
                        post = result.forum,
                        totalComment = result.replies.size
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        notificationMessage = error.asUiText()
                    )
                }
            }
    }


}