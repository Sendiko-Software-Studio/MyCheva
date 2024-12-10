package com.mycheva.app.forum.comment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.forum.comment.data.PostReplyRequest
import com.mycheva.app.forum.comment.domain.CommentRepository
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
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(CommentScreenState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            userId = userId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CommentScreenState())

    fun onEvent(event: CommentEvent) {
        when (event) {
            is CommentEvent.OnCommentTextChange -> changeCommentText(event.value)

            CommentEvent.OnClearState -> clearState()

            is CommentEvent.OnLoadData -> loadData(event.token, event.forumId)
            is CommentEvent.OnPostComment -> postReply(event.token, event.userId, event.forumId)
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                notificationMessage = "",
                isCommentPosted = false,
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
                _state.update { it.copy(isError = true, notificationMessage = "Comment can't be empty.") }
            }
            _state.update { it.copy(isLoading = true) }
            val data = PostReplyRequest(
                userId = userId.toInt(),
                forumId = forumId.toInt(),
                content = state.value.commentText
            )
            repository.postReply(token = "Bearer $token", request = data)
                .onSuccess { result ->
                    _state.update { it.copy(isLoading = false, isCommentPosted = true) }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            notificationMessage = error.message.toString()
                        )
                    }
                }

        }

    private fun loadData(token: String, forumId: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.update { it.copy(isLoading = true) }
        repository.loadData("Bearer $token", forumId)
            .onSuccess { result ->
                _state.update { it.copy(comments = result.forum.replies.reversed(), isLoading = false) }
            }
            .onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        notificationMessage = error.message.toString()
                    )
                }
            }
    }


}