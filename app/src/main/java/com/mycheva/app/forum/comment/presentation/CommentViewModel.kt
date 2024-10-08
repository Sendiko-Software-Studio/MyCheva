package com.mycheva.app.forum.comment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.core.network.NOT_FOUND
import com.mycheva.app.core.network.SERVER_ERROR
import com.mycheva.app.forum.comment.data.PostReplyRequest
import com.mycheva.app.forum.comment.domain.CommentRepositoryImpl
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
    private val repository: CommentRepositoryImpl
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
            is CommentEvent.OnCommentTextChange -> _state.update {
                it.copy(commentText = event.value)
            }

            CommentEvent.OnClearState -> _state.update {
                it.copy(
                    isLoading = false,
                    isError = false,
                    notificationMessage = "",
                    isCommentPosted = false,
                    commentText = ""
                )
            }

            is CommentEvent.OnLoadData -> loadData(event.token, event.forumId)
            is CommentEvent.OnPostComment -> postReply(event.token, event.userId, event.forumId)
        }
    }

    private fun postReply(token: String, userId: String, forumId: String) {
        _state.update { it.copy(isLoading = true) }
        val data = PostReplyRequest(
            userId = userId.toInt(),
            forumId = forumId.toInt(),
            content = state.value.commentText
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.postReply("Bearer $token", data)
                .onSuccess {
                    _state.update {
                        it.copy(
                            notificationMessage = "Reply berhasil di post!",
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    when(error.message) {
                        NOT_FOUND -> _state.update {
                            it.copy(
                                notificationMessage = "Post tidak ditemukan.",
                                isLoading = false,
                                isError = true
                            )
                        }
                        SERVER_ERROR -> _state.update {
                            it.copy(
                                notificationMessage = "Server error.",
                                isLoading = false,
                                isError = true
                            )
                        }
                    }
                }
        }
    }

    private fun loadData(token: String, forumId: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadData("Bearer $token", forumId)
                .onSuccess { data ->
                    _state.update {
                        it.copy(
                            post = data.first,
                            comments = data.second,
                            totalComment = data.second.size
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            notificationMessage = "Server error.",
                            isLoading = false,
                            isError = true
                        )
                    }
                }
        }
    }


}