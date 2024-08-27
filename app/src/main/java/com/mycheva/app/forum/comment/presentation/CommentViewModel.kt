package com.mycheva.app.forum.comment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.forum.comment.data.GetForumResponse
import com.mycheva.app.forum.comment.data.PostReplyRequest
import com.mycheva.app.forum.comment.data.PostReplyResponse
import com.mycheva.app.forum.comment.domain.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        val request = repository.postReply(token = "Bearer $token", request = data)
        request.enqueue(
            object : Callback<PostReplyResponse> {
                override fun onResponse(
                    call: Call<PostReplyResponse>,
                    response: Response<PostReplyResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        201 -> _state.update {
                            it.copy(
                                isCommentPosted = true,
                                notificationMessage = response.body()!!.message,
                            )
                        }

                        else -> _state.update {
                            it.copy(
                                isError = true,
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }

                override fun onFailure(p0: Call<PostReplyResponse>, p1: Throwable) {
                    _state.update {
                        it.copy(
                            isError = true,
                            notificationMessage = "Server error."
                        )
                    }
                }

            }
        )
    }

    private fun loadData(token: String, forumId: String) {
        _state.update { it.copy(isLoading = true) }
        val request = repository.loadData("Bearer $token", forumId)
        request.enqueue(
            object : Callback<GetForumResponse> {
                override fun onResponse(
                    call: Call<GetForumResponse>,
                    response: Response<GetForumResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> _state.update {
                            val replyEmpty = response.body()?.forum?.replies == null
                            val totalReplies = if (replyEmpty) 0 else response.body()!!.forum.replies.size
                            it.copy(
                                comments = response.body()?.forum?.replies ?: emptyList(),
                                totalComment = totalReplies,
                                post = response.body()!!.forum,
                                isCommentPosted = false
                            )
                        }

                        else -> _state.update {
                            it.copy(
                                isError = true,
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }

                override fun onFailure(p0: Call<GetForumResponse>, p1: Throwable) {
                    _state.update {
                        it.copy(
                            isError = true,
                            notificationMessage = "Server error."
                        )
                    }
                }

            }
        )
    }


}